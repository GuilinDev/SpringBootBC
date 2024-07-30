# Use a base image with Java 21 runtime
FROM azul/zulu-openjdk:21

# Set the working directory in the container
WORKDIR /app

# Copy the project's jar file into the container
COPY target/hello-0.0.1-SNAPSHOT.jar /app/hello-app.jar

# Expose the port the app runs on
EXPOSE 8090

# Define the command to run the app
ENTRYPOINT ["java", "-jar", "/app/hello-app.jar"]
