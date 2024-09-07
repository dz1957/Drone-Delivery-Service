# Drone Delivery Service
We designed and developed a system to monitor deliveries of grocery items to customers. We built a multi-container docker application, scaling by starting
multiple instances/containers of DeliveryService and using Nginx to distribute traffic. When a large amount of REST API requests come into the system, Nginx will load balancing the
requests across the DeliveryService containers to achieve high scalability.

Docker containers are lightweight and portable, making them easy to scale. Nginxload balancing can further improve scalability by distributing traffic across multiple instances of
DeliveryService to handle a large number of concurrent requests

# Quickstart
We can simply run `make && make up` to start our service. For those that do not have `make` installed, we can effectively do the same thing by running the following:
```
docker build -t gatech/backend -f ./images/Dockerfile.backend ./backend
docker build -t gatech/frontend -f ./images/Dockerfile.frontend ./frontend
docker-compose -p gatech -f docker-compose.yml up -d
```
The first two commands are building the backend and frontend images defined by our dockerfiles, and the third is using docker-compose to deploy the service locally.
To break down the first command we can read it as "build an imageusing the `./images/Dockerfile.backend` file from the `./backend` directory and tag it as `gatech/backend`"
To break down the third command, we can read it as "deploy a service called `gatech` as defined in file `docker-compose.yml`

# Frontend
The front end service defines a single html file that calls our backend service and displays the randomly generated value. Our nginx service is deployed on port 3001
You should be able to navigate to [http://localhost:3001](http://localhost:3001) to view the page

# Backend
It is important to note that since our backend and frontend are split between two containers, we need to deal with [cross-origin resource sharing](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing). You see that this application has been pre-defined to allow our front end service to access our backend using Spring's `@CrossOrigin` annotation. If you deviate from our starter code, and split your frontend container from your backend, you will also need to ensure your web application sets this response header.
Our backend is bootstrapped to communicate with our database container using the Java Persistenace API (JPA) with Hibernate.
You will see the configuration in the `application.properties` file. Note that we have hibernate configured to automatically create the table as needed


# Maven 
This project uses [Apache Maven](https://maven.apache.org/) to manage itself. 
You will see the dependencies defined in `/backend/pom.xml`, and the maven commands called by the backend's Dockerfile

