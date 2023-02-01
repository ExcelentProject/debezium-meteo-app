# debezium-crypto-app Project

This project is created for testing purposes only! Core idea behind the application is to create data source
that can produce traffic for debezium connector in order to test this connector in long-running environment.

Application creates Rest request to [coincap api](https://api.coincap.io/v2/assets) in the interval configured
by property `update.period` and persist acquired data to PostgreSQL database (default database name is `debezium_crypto`).
Version of the database can be from 11-15 as Debezium support matrix indicates.

## Running the application in dev mode

Before you can run the actual app you have to deploy the database. There is prepared docker image for you.
You can run the database as:
```shell script
cd database
docker build -t tealc-postgres:latest .
docker run --name postgresql -e POSTGRES_USER=test -e POSTGRES_PASSWORD=test -p 5432:5432 -v /tmp/postgresql/:/var/lib/postgresql/data -d tealc-postgres:latest
```
Keep in mind that your username and password must be the same that you pass to debezium-crypto application.
You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

This application can be build as native image:
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true
```
Once native image is build you can build docker image from that as:
```shell script
docker build -f src/main/docker/Dockerfile.native -t quay.io/tealc/debezium-crypto-app .
```

After image is build you can start the container like this:
```shell script
docker run --name app -p 127.0.0.1:8080:8080 quarkus/debezium-crypto-app ./application "-Dquarkus.http.host=0.0.0.0" "-Dupdate.period=2s" "-Dquarkus.datasource.jdbc.url=jdbc:postgresql://${CONTAINER_IP_OR_HOST}:5432/debezium_crypto"
```
[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
