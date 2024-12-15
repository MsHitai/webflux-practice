Currently implemented a product-service application that uses reactive NoSQL database (MongoDb). 
To successfully start the application, use the docker-compose.yml file in the project with the set-up mongo-db, by running this command:

docker compose up -d

**To be Done**:

*Add a second module - a service that will use reactive database (PostgreSQL).
*Add a gateway that will check authorization and which will use Redis to get the session.
