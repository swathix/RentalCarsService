# RentalCarsService

This is a Spring Boot based Maven project. RestTemplate --> Used for Request and Response objects modeling.

ObjectMapper --> Has been used to Map Json string to the required bean class objects Bbuilt a wrapper class CarCollection around List and size of the list and used Spring framework's ObjectMapper to convert/map the Json string to CarCollection.

Unit test: WireMockRule --> Has been used to mock the Json response from server

Build: mvn clean install
