# Spring-pet application

**Experimental project to try Spring, SpringBoot, SpringData, Docker, WebFlux.**
    
### **Deployment and start using Docker Compose**
- From your project directory, start up your application by running:

        docker-compose up --build -d

### Default properties
**_Database access_**
- **name** : test
- **host** :
    - (external docker access) : 192.168.99.100
    - (internal docker access) : mongo
- **port** : 27017

### **Custom properties**

**_Database parameters in application properties_**

If you want to change default Database properties, set them in the command line or in the 'application.properties' file.

If application is started in Docker and you use command line parameters, you should set them in the 'Docker' file.

- To change the path to the database, change the properties 'spring.data.mongodb.host' and 'spring.data.mongodb.port'.
    -  Example for external access: 
        - spring.data.mongodb.host=192.168.99.100
        - spring.data.mongodb.port=27000
    - Example for internal access: 
        - spring.data.mongodb.host=mongo
        - spring.data.mongodb.port=27017

- To change Database name, set (change) the property 'spring.data.mongodb.database'.

    - Example:

            spring.data.mongodb.database=SomeDbName

**_Database parameters in Docker_**

- To change the DB access parameters, set them in the 'docker-compose.yml' file.

    - Example:
           
            ports:
                - "27000:27017"
                

**_Additional command line parameters_**
        
- If you run the application locally, add next parameters to the command line
for disabling warnings about illegal reflective access operations in Java 9:
        
        --add-opens=java.base/java.nio=ALL-UNNAMED
        --add-opens=java.base/sun.nio.ch=ALL-UNNAMED
        --add-opens=java.base/java.lang=ALL-UNNAMED
        --add-opens=java.base/java.lang.invoke=ALL-UNNAMED
        
### **Useful docker commands**:
**Show all containers**:
    docker ps -a

**Show running containers**:
    docker ps

**Stop running container**:
    docker stop <id>

**Stop all running containers**:
    docker stop $(docker ps -a -q)

**Start container**:
    docker start <id>

**Remove container**:
    docker rm <id>

**Remove all containers**:
    docker rm $(docker ps -a -q)
        
**Show all images**:
    docker images

**Remove image**:
    docker rmi <id>

**Remove unused data (containers, networks, images)**:
    docker system prune -a -f

### **REST API**
**_CRUD operations for materialrequest entity._**

- MaterialRequest entity fields:

        id              : String
        requestNumber   : Integer
        customerName    : String
        priority        : Integer
        invoice         : String
    
- Root endpoint for standard access:

        /materialrequest

- Root endpoint for reactive access:

        /materialrequest/flux        

- **GET** (type JSON):

        /get 
            Returns all entities.
        
        /get/{id}
            Returns entitiy by id.
        
        /getByCustomerName?customerName={customer Name}
            Returns entities with specified customer name.
        
        /getByInvoice?invoice={invoice}
            Returns entities with specified invoice.

- **POST** (type JSON):

        /save
            Saves the entity and returns it.
            If the id parameter is missing, it will be generated automatically.
        
            Example:
                {
                    "requestNumber": 2,
                    "customerName": "JD",
                    "priority": 4,
                    "invoice": "A51"
                }

        
- **DELETE**

        /delete/{id}
            Deletes the entity with the specified id.

        Example:
            http://192.168.99.100:8080/materialrequest/get
        
### **Performance testing**
You can compare performance of GET operations in standard access and reactive access.
These results show the difference between blocking and nonblocking sccess.
But this test doesn't measure real time of data obtaining.
For the correct comparison, you should use REST-client to emulate synchronous access of many users.
For example, you can use JMeter.

- **Test getting a list of entities**: /materialrequest/test/get/{number}
    - **number**: number of read operations you want to perform

- **Test getting an entity**: /materialrequest/test/get/{id}/{number}
    - **id**: entity identifier
    - **number**: number of read operations you want to perform
    
    Returns JSON object.
    
Example 1:

        http://192.168.99.100:8080/materialrequest/test/get/100
        Response:
        {
            "number of requests" : 100,
            "acceleration factor" : 24,
            "simple response time (ns)" : 170544518,
            "number of entities" : 3,
            "reactive response time (ns)" : 6926874,
            "method name" : "findAll"
        }
    
Example 2:

        http://192.168.99.100:8080/materialrequest/test/get/5a61d14ac9e77c0001ee832e/100
        Response:
        {
            "number of requests" : 100,
            "acceleration factor" : 12,
            "simple response time (ns)" : 139455240,
            "number of entities" : 1,
            "reactive response time (ns)" : 11381449,
            "method name" : "findById"
        }
