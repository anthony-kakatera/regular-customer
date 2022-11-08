FROM openjdk:8-jdk-alpine
EXPOSE 8080 3306
ADD target/regular-customer-endpoint.jar regular-customer-endpoint.jar
ENTRYPOINT ["java","-jar","/regular-customer-endpoint.jar"]