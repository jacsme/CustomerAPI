# CustomerAPI

This project is developed using Java 1.8, Spring Boot, Jacoco, Junit and Mockito and Maven 3.3.9. Spring Boot Framework is implemented as it will give some advantages for the setup, we don't need to setup the application server because it has its built-in appserver. Follow the below instruction and email jack.lord.hermoso@gmail.com if you have any comments and questions.

# Configuration
## Maven:
- Download and extract apache-maven-3.3.9 
- Save it to c:\apache-maven-3.3.9
## Logger File:
- Create a folder in C:/Customer
## Java:
- Download Java 1.8 and save it to c:\Program Files\Java\jdk1.8.0_91
## Environment Variable:
- Setup the System Variable with the following:
	```	
	-- Variable			| Value	
	-- CATALINA_HOME   	| c:\apache-maven-3.3.9
	-- M2_HOME			| c:\apache-maven-3.3.9
	-- M2				| %M2_HOME%\bin
	-- JAVA_HOME		| c:\Program Files\Java\jdk1.8.0_91
	-- Path				| %JAVA_HOME%\bin; %M2%;  [add this at the end of the path value]
	```
## Eclipse:
- Download the latest Eclipse IDE with maven plugins	
- Fork/Clone this project to your local repository, and import the project to eclipse
- Refresh the project
- Right click on the project, select properties. On the properties window, configure the Java Build Path menu and point the JRE System Library to jdk1.8.0_91 (or wahtever version you have). 
- And on the Java Compiler menu, select the version 1.8 on the drop down box.
- Make a clean and build process to your local project in eclipse.
- Update your maven project by doing a right click on the project, select Maven -> Update Project
- Make a maven clean and install by doing a right click on the project, select
	1. Run as -> Maven clean
	2. Run as -> Maven install
## Data:
	```
	- In your mysql workbench make a new connection and schema 
	  
	  - Connection Name - Database
	  - Schema Name - customerschema
	  
	  ### Note : 
	  Tables and initial values will be created by the system upon running. So keep in mind that every time you restrated the system. All the tables and data will be wiped out and regenerated the tables.
	```
## Run the program:
- Run the spring boot application, on your project right click on the CustomerApplication located in the com.customer package, Run As -> Java Application
- The system will be ran in http://localhost:8080/CustomerRecords and it is predefined in my postman
- Download the postman from Chrome which will be used for accessing the program for integration testing. Import the predefined collection and run it, provided that the system is up and running, it is located in main project [CustomerAPI.postman_collection.json] of the project CustomerAPI.

## Run Jacoco Test Coverage
- Make a clean and build process to your local project in eclipse.
- Make a maven clean, install, and test by doing a right click on the project, select
	1. Run as -> Maven clean
	2. Run as -> Maven install
	3. Run as -> Maven test
- Right click the target/jacoco-ut/index.html file and open it as Web Browser. 
 
## API URL
## You can run these Available URL in postman collection, with Token Autorization:

1. Add Customer Record - http://localhost:8080/CustomerRecords/v1/customer/saveRecords [POST]
	- This will create a new customer records
	- Request 
    - Headers:
      - ``` Authorization - Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU0MTY2NDYwMywiaWF0IjoxNTQxMDU5ODAzfQ.jrsrH64uI3TavgNXswCGl6jySPL4FxhjjiRItzDDiFboO5TpTFhtg6WU_OLHNaCif3fBhhPGANU_ovDywmGURA ```
      - Content-Type - application/json
    - Body :
      ``` 
      [{
		"customerId": "CUST-000001",
		"dateOfBirth": 1541077200000,
		"firstName": "Sample",
		"lastName": "Test"
	  }]
	``` 
  - Response
    - Body :
        ``` 
        {
            "status": "SUCCESS",
            "message": "Record save is successful"
        }
		```	
2. Update Customer Record - http://localhost:8080/CustomerRecords/v1/customer/updateRecords [PUT]
	- This will retrieve the records from the database to update the customer records
	- Request 
	- Headers:
      - ``` Authorization - Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU0MTY2NDYwMywiaWF0IjoxNTQxMDU5ODAzfQ.jrsrH64uI3TavgNXswCGl6jySPL4FxhjjiRItzDDiFboO5TpTFhtg6WU_OLHNaCif3fBhhPGANU_ovDywmGURA ```
      - Content-Type - application/json
	  - Body :
	  ```
	  [{
		"id": 1,
		"customerId": "CUST-000001",
		"dateOfBirth": 1541077200000,
		"firstName": "Sample",
		"lastName": "Test"
	  }]
	```
  - Response
    - Body :
      ```  
        {
			"status": "SUCCESS",
			"message": "Record update is successful"
		}
	  ```
 3. Delete Customer Records - http://localhost:8080/CustomerRecords/v1/customer/deleteRecords/CUST-000003 [DELETE]
	- This will retrieve the records from the database to delete a record using customer id
	- Request 
	- Headers:
      - ``` Authorization - Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU0MTY2NDYwMywiaWF0IjoxNTQxMDU5ODAzfQ.jrsrH64uI3TavgNXswCGl6jySPL4FxhjjiRItzDDiFboO5TpTFhtg6WU_OLHNaCif3fBhhPGANU_ovDywmGURA ```
      - Content-Type - application/json
  - Response
    - Body :
        ``` 
        {
			"status": "SUCCESS",
			"message": "Record delete is successful"
		}
		```
 4. Get Customer Record All - http://localhost:8080/CustomerRecords/v1/customer/getAllRecords [GET]
	- This will retrieve the records from the database to get all the Customer Details
	- Request 
    - Headers:
      - ``` Authorization - Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU0MTY2NDYwMywiaWF0IjoxNTQxMDU5ODAzfQ.jrsrH64uI3TavgNXswCGl6jySPL4FxhjjiRItzDDiFboO5TpTFhtg6WU_OLHNaCif3fBhhPGANU_ovDywmGURA ```
      - Content-Type - application/json
  - Response
     Body :
     ```
        [
			{
				"id": 1,
				"customerId": "CUST-000001",
				"dateOfBirth": 1541077200000,
				"firstName": "Sample1",
				"lastName": "Test"
			},
			{
				"id": 2,
				"customerId": "CUST-000002",
				"dateOfBirth": 1541077200000,
				"firstName": "Sample2",
				"lastName": "Test"
			}
		]
		```
 5. Get Customer Record CustomerId - http://localhost:8080/CustomerRecords/v1/customer/getRecord/CUST-000001 [GET]
	- This will retrieve the records from the database to get the - Specific Customer Details
	- Request 
      - Headers:
      - ``` Authorization - Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU0MTY2NDYwMywiaWF0IjoxNTQxMDU5ODAzfQ.jrsrH64uI3TavgNXswCGl6jySPL4FxhjjiRItzDDiFboO5TpTFhtg6WU_OLHNaCif3fBhhPGANU_ovDywmGURA ```
      - Content-Type - application/json
  - Response
    - Body :
    ``` 
        [
			{
				"id": 1,
				"customerId": "CUST-000001",
				"dateOfBirth": 1541077200000,
				"firstName": "Sample",
				"lastName": "Test"
			}
		]
		```
6. Generate a new token -  http://localhost:8080/auth [POST]
  - This will generate the token for the api to be used.
  - Request 
    - Headers:
      - Content-Type - application/json
    - Body:
      ``` 
	  {
        "username": "admin",
        "password": "admin"
      }
	  ```
  - Response
    - Body :
    ```
      {
       "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU0MTY2NDYwMywiaWF0IjoxNTQxMDU5ODAzfQ.jrsrH64uI3TavgNXswCGl6jySPL4FxhjjiRItzDDiFboO5TpTFhtg6WU_OLHNaCif3fBhhPGANU_ovDywmGURA"
      } ```   
	  
# Credentials
- The system is protected and uses JWT for authentication and it can only access by sending the token and if you do not provide the token the system will generate an error stating that you are unauthorized
- You can generate the token by accessing the http://localhost:8080/auth and supplying the user and password for you to get the token. 
- Once the token is generated, you can copy it and paste as a header Authorization parameter of each api's  
```
Authorization - Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU0MTY2NDYwMywiaWF0IjoxNTQxMDU5ODAzfQ.jrsrH64uI3TavgNXswCGl6jySPL4FxhjjiRItzDDiFboO5TpTFhtg6WU_OLHNaCif3fBhhPGANU_ovDywmGURA
```

### You can use these credentials for generating the token
- Admin - admin:admin
- User - user:password
  
