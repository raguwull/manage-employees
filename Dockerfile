FROM openjdk:8
EXPOSE 8080
ADD target/manage-employees.jar manage-employees.jar
ENTRYPOINT ["java", "-jar", "/manage-employees.jar"]