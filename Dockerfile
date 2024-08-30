# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the maintainer
LABEL maintainer="harikrishna03092@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's JAR file

ARG JAR_FILE=target/UserRecertification-0.0.1-SNAPSHOT.jar

# Add the application's JAR file to the container
ADD ${JAR_FILE} app.jar

# Run the JAR file
ENTRYPOINT ["java","-jar","/app.jar"]
