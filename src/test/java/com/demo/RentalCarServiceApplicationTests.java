package com.demo;

import com.demo.config.RentalCarConfiguration;
import com.demo.model.Car;
import com.demo.model.CarCollection;
import com.demo.model.MetaData;
import com.demo.model.Metrics;
import com.demo.model.PerDayRent;
import com.demo.model.RentalCount;
import com.demo.service.RentalCarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RentalCarServiceApplicationTests.CustomConfiguration.class, RentalCarConfiguration.class})
public class RentalCarServiceApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RentalCarService rentalCarService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testGetCarsOfSpecificMakeColor() {
    	String apiCall = "allCars";
    	String car3Vin= "JCF6857953h76";
    	Car car1 = new Car("Mazda", "Mazda6", "JTF68hf455479", new MetaData("Blue", "Excellent Condition"), new PerDayRent(23.55f, 0.0f), new Metrics(1090.0f, 2305.6f, null));
    	Car car2 = new Car("Tesla", "ModelX", "JCF6857953h76", new MetaData("Red", "Moderate"), new PerDayRent(23.10f, 0.25f), new Metrics(2100.0f, 1987.6f, null));
    	Car car3 = new Car("Tesla", "ModelX", car3Vin, new MetaData("Blue", "Scratches on Car Body"), new PerDayRent(32.0f, 0.15f), new Metrics(990.0f, 1155.6f, null));
    	//negative test case
    	List<Car> mockCars = new ArrayList<Car>();
    	mockResponse(apiCall, mockCars);
        List<Car> cars = rentalCarService.getCarsOfSpecificMakeColor();
        TestCase.assertEquals(0, cars.size());
        mockCars = new ArrayList<Car>();
        //negative test case
        mockCars.add(car1); 
    	mockResponse(apiCall, mockCars);
        cars = rentalCarService.getCarsOfSpecificMakeColor();
        TestCase.assertEquals(0, cars.size());
        //positive test case
        mockCars = new ArrayList<Car>();
    	mockCars.add(car2); 
    	mockCars.add(car3);
    	mockResponse(apiCall, mockCars);
        cars = rentalCarService.getCarsOfSpecificMakeColor();
        TestCase.assertEquals(1, cars.size());
        TestCase.assertEquals(true, cars.get(0).getVin().equals(car3Vin));
    }
    
    @Test
    public void testGetCarsWithLowestPrice() {
    	String apiCall = "allCars";
    	String car1Vin = "JTF68hf455479";
    	String car3Vin = "GDF68h562g529";
    	Car car1 = new Car("Tesla", "ModelX", car1Vin, new MetaData("Blue", "Excellent Condition"), new PerDayRent(47.00f, 0.0f), new Metrics(1090.0f, 2305.6f, null));
    	Car car2 = new Car("Ferrari", "ModelX", "JCF6857953h76", new MetaData("Red", "Moderate"), new PerDayRent(303.10f, 0.25f), new Metrics(2100.0f, 1987.6f, null));
    	Car car3 = new Car("Tesla", "ModelX", car3Vin, new MetaData("Blue", "Scratches on Car Body"), new PerDayRent(47.00f, 0.15f), new Metrics(990.0f, 1155.6f, null));
    	//negative test case
    	List<Car> mockCars = new ArrayList<Car>();
    	mockResponse(apiCall, mockCars);
        List<Car> cars = rentalCarService.getCarsWithLowestPrice();
        TestCase.assertEquals(0, cars.size());
        
        //positive test case
        mockCars.add(car1);
    	mockCars.add(car2); 
        mockResponse(apiCall, mockCars);
        cars = rentalCarService.getCarsWithLowestPrice();
        TestCase.assertEquals(1, cars.size());
        TestCase.assertEquals(true, cars.get(0).getVin().equals(car1Vin));
        
        //positive test case
        mockCars = new ArrayList<Car>();
        mockCars.add(car1);
    	mockCars.add(car2); 
        mockCars.add(car3);
    	mockResponse(apiCall, mockCars);
        cars = rentalCarService.getCarsWithLowestPrice();
        TestCase.assertEquals(2, cars.size());
        TestCase.assertEquals(true, cars.get(0).getVin().equals(car1Vin));
        TestCase.assertEquals(true, cars.get(1).getVin().equals(car3Vin));
    }
    
    @Test
    public void testGetCarsWithLowestPriceAfterDiscount() {
    	String apiCall = "allCars";
    	String car1Vin = "JTF68hf455479";
    	String car3Vin = "GDF68h562g529";
    	Car car1 = new Car("Tesla", "ModelX", car1Vin, new MetaData("Blue", "Excellent Condition"), new PerDayRent(40.00f, 0.0f), new Metrics(1090.0f, 2305.6f, null));
    	Car car2 = new Car("Ferrari", "ModelX", "JCF6857953h76", new MetaData("Red", "Moderate"), new PerDayRent(303.10f, 0.25f), new Metrics(2100.0f, 1987.6f, null));
    	Car car3 = new Car("Tesla", "ModelX", car3Vin, new MetaData("Blue", "Scratches on Car Body"), new PerDayRent(50.00f, 0.20f), new Metrics(990.0f, 1155.6f, null));
    	//negative test case
    	List<Car> mockCars = new ArrayList<Car>();
    	mockResponse(apiCall, mockCars);
        List<Car> cars = rentalCarService.getCarsWithLowestPriceAfterDiscount();
        TestCase.assertEquals(0, cars.size());
        
        //positive test case
        mockCars.add(car1);
    	mockCars.add(car2); 
        mockResponse(apiCall, mockCars);
        cars = rentalCarService.getCarsWithLowestPriceAfterDiscount();
        TestCase.assertEquals(1, cars.size());
        TestCase.assertEquals(true, cars.get(0).getVin().equals(car1Vin));
        
        //positive test case
        mockCars = new ArrayList<Car>();
        mockCars.add(car1);
    	mockCars.add(car2); 
        mockCars.add(car3);
    	mockResponse(apiCall, mockCars);
        cars = rentalCarService.getCarsWithLowestPriceAfterDiscount();
        TestCase.assertEquals(2, cars.size());
        TestCase.assertEquals(true, cars.get(0).getVin().equals(car1Vin));
        TestCase.assertEquals(true, cars.get(1).getVin().equals(car3Vin));
    }
    
    @Test
    public void testGetHighestRevenueGeneratingCar() {
    	String apiCall = "allCars";
    	String car1Vin = "JTF68hf455479";
    	String car3Vin = "GDF68h562g529";
    	Car car1 = new Car("Mazda", "Mazda6", car1Vin, new MetaData("Blue", "Excellent Condition"), new PerDayRent(40.00f, 0.0f), new Metrics(1090.0f, 2305.6f, new RentalCount(3,300)));
    	Car car2 = new Car("Ferrari", "XX", "JCF6857953h76", new MetaData("Red", "Moderate"), new PerDayRent(100.00f, 0.25f), new Metrics(2100.0f, 1987.6f,  new RentalCount(4,100)));
    	Car car3 = new Car("Tesla", "ModelX", car3Vin, new MetaData("Blue", "Scratches on Car Body"), new PerDayRent(50.00f, 0.20f), new Metrics(1990.0f, 1155.6f,  new RentalCount(3,150)));
    	
    	List<Car> mockCars = new ArrayList<Car>();
        //positive test case
        mockCars.add(car1);
    	mockCars.add(car2); 
        mockResponse(apiCall, mockCars);
        Car car = rentalCarService.getHighestRevenueGeneratingCar();
        TestCase.assertNotNull(car);
        TestCase.assertEquals(true, car.getVin().equals(car1Vin));
        
        //positive test case
        mockCars = new ArrayList<Car>();
        mockCars.add(car1);
    	mockCars.add(car2); 
        mockCars.add(car3);
    	mockResponse(apiCall, mockCars);
        car = rentalCarService.getHighestRevenueGeneratingCar();
        TestCase.assertNotNull(car);
        TestCase.assertEquals(true, car.getVin().equals(car1Vin));
    }
    
    /**
     * Helper method accepts list of Car objects, converts into Json string mock the result for rest service call
     * @param methodName -  Rest end point method name
     * @param mockCars - car objects that will be returned in mock response
     */
    //Note: We built a wrapper class CarCollection around List<Car> and size of the list 
    //and used Spring framework's ObjectMapper to convert/map the Json string to CarCollection
	private void mockResponse(String methodName, List<Car> mockCars) {
    	CarCollection testCars = new CarCollection();
    	testCars.setCollections(mockCars);
    	testCars.setSize(mockCars.size());
    	String s = "";
    	try {
			s = objectMapper.writeValueAsString(testCars);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	wireMockRule.resetAll();
    	stubFor(get(urlPathMatching("/demo/v1/"+methodName+"*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(s)));
	}
    
    @Configuration
    public static class CustomConfiguration {

        @Bean
        ObjectMapper objectMapper(){
            return  new ObjectMapper();
        }

    }
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8089).httpsPort(8443));


}
