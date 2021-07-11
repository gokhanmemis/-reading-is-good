# reading-is-good

This is a Springboot Rest Service project. This project was started with spring initializr (Spring Web and H2 database was selected).  

**This project contains following packages inner the java package**.
 - config  ( This package contains Spring, JWT (Web Token) , Swagger configurations)
 - constant  (This package contains API constants)
 - controller (This package contains Rest Services' controllers. This controllers are Book, Customer, Order, Statistic, User)
 - log (This package contains log infrastructure. All the log mechanism in the application is run from the classes in this package.  )
 - model (This package contains model class. This classes are Book, Customer, Order, User)
 - repository (This package contains repository classes and interfaces. Database operations are done through these classes and interfaces. Also, for the map to database column, this package contains RowMapper class.)
 - service  (This package contains services classes. This classes are Book, Customer, Order, Statistic.)
 - util (This package contains some util classes. This classes are related to Exception and Message mechanism )
 - validation (This package contains validation classes. Received data from rest service, use this validation class before to go database.)
 
 **This project contains following packages inner the test package**. This package contains test classes. It is packaged according to the corresponding java class package.
 
 - controller 
 - repository 
 - service 
 - util


# How to run?
If you want to run without docker firstly (It is prerequisite to have maven, java and git installed) :
	
    git clone https://github.com/gokhanmemis/reading-is-good.git

and then open **reading-is-good** folder and run following command:
	
    mvn clean install

If you want to run with docker (It is prerequisite to have maven, docker and git installed) : 

    git clone https://github.com/gokhanmemis/reading-is-good.git

and then open **reading-is-good** folder and run following commands:

    docker build -t reading-is-good .
    nohup docker run -i -p 8080:8080 reading-is-good </dev/null &>/dev/null &
After that, you can call services via postman (localhost:8080).

## Some functions of the application

If you want to test following services, firstly you can call login method. This POST method url is **localhost:8080/user**. You must add body parameters to username : **username** and password : **pwd**.
After that, login method return bearer token, you add this token request's autharization. This autharization type is **Bearer Token**. All methods' sample request are contains in the postman file (https://github.com/gokhanmemis/reading-is-good/blob/main/getir.postman_collection.json).

 - Registering new customer (POST : localhost:8080/getir/api/customer/ )
 - Placing a new order (POST : localhost:8080/getir/api/order/)
 - Tracking the stock of books (POST : localhost:8080/getir/api/book/)
 - List all orders of the customer (GET : localhost:8080/getir/api/order/findAllWithAllData)
 - Viewing the order details- (GET : localhost:8080/getir/api/order/{id})
 - Query Monthly Statistics (GET : localhost:8080/getir/api/statistic/statistic)

## Base used technology / methods

 - Java 11, Spring Framework, Spring Boot
 - Relational H2 database
 - Restful Endpoints
 - Clean Code
 - Test ( Unit & Integration) %73 functionality coverage
	 - https://github.com/gokhanmemis/reading-is-good/blob/main/test_result.png
 - Containerize (You can run application via Docker. **How to run** section describes it.)
 - Validations (Received data from rest service, use this validation class before to go database. Services classes contains @Validation annotation. If you add this annotation, service firstly call annotation methods.)
 - Secure endpoints with bearer token
 - It was define that success and error response models. All responses are same type. All responses contains:
	 - Status (OK, NOK)
	 - messageCode (INFO, ERROR, WARNING)
	 - messageList (If messages are contains, this field is returned)
	 - data 
 - Postman ((https://github.com/gokhanmemis/reading-is-good/blob/main/getir.postman_collection.json))
 - Logging (all changes on entities are logging. Also all requests are logging. Spring Interceptor was used.)
 - Swagger (For api spesification, swagger is used. (http://localhost:8080/swagger-ui.html) )

## Additional methods

If you want to test following services, firstly you can call login method. This POST method url is **localhost:8080/user**. You must add body parameters to username : **username** and password : **pwd**.
After that, login method return bearer token, you add this token request's autharization. This autharization type is **Bearer Token**. All methods' sample request are contains in the postman file (https://github.com/gokhanmemis/reading-is-good/blob/main/getir.postman_collection.json).

 - Customer Controller contains : 
	 - persist new customers (POST : localhost:8080/getir/api/customer/ ), 
	 - query all orders of the customer **with paging** (GET : localhost:8080/getir/api/customer/order/{customerId}?page=0&size=3)
 - Book Controller contains : 
	 - persist new book (POST : localhost:8080/getir/api/book/), 
	 - update book’s stock (POST : localhost:8080/getir/api/book/)
 - Order Controller contains : 
	 - persist new order (POST : localhost:8080/getir/api/order/), 
	 - query order by Id (GET : localhost:8080/getir/api/order/{id}), 
	 - list orders by date interval (POST : localhost:8080/getir/api/order/getOrdersByDate)
 - Statistics Controller
	 - serve customer’s monthly order statistics (GET : localhost:8080/getir/api/statistic/statistic)