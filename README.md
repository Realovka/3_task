# Unauthorized deliveries

REST API application containing functionality for reading information from files and for writing
to a file. It can write information to a database. Application has two entities: Login and 
Posting. We can get information from database about postings with filters and find postings
for a certain period and unauthorized (or authorized) deliveries.


## How to build

1) clone this repository
2) create schema in database MySQL by script from db.sql file
3) change such values as spring.datasource.username, spring.datasource.password, server.port in 
application.properties file to match your database and port
4) run command mvn package (you create jar file application)
5) run command java -jar target/testtask-0.0.1-SNAPSHOT.jar (you run application)

## How to work

You should use HTTP-client (Postman) for work and use port which your have written in application.properties 
1) to get all logins from file you should do http://localhost:8081/api/v1/logins (GET)
   ![get_logins](https://user-images.githubusercontent.com/61760081/162722484-2e88e333-e926-44a8-94c8-dd7b1abe23c7.jpg)
2) to get all postings from file you should do http://localhost:8081/api/v1/postings (GET)
   ![get_postings_from_file](https://user-images.githubusercontent.com/61760081/162723148-3617cb68-2f5d-4f53-9ab0-58962aa3c78b.jpg)
3) to add a boolean field "authorized delivery" to the data from postings.csv, which will indicate that the User Name
(postings.csv) is in the list AppAccountName (logins.csv) and IsActive you should do
   http://localhost:8081/api/v1/postings/file (POST)
4) to save data from logins.csv in MySQL you should do http://localhost:8081/api/v1/logins (POST)
5) to save data from postings.csv in MySQL you should do http://localhost:8081/api/v1/postings/db (POST)
6) to get postings from database for the period and with filter authorized or not you should do
   http://localhost:8081/api/v1/postings/period?start=2020-01-01&end=2021-01-01&authorized=false (GET)
   as the start parameter, you need to specify the date of the beginning of the period (inclusive) 
   and the end of the period (not inclusive) and parameter authorized can be true or false
   ![get_by_period](https://user-images.githubusercontent.com/61760081/162726117-efa1834f-a7c1-43c7-966d-3d016af408f1.jpg)
