# Use the official OpenJDK base image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/grocery-booking-api.jar /app

# Expose the port that the application will run on
EXPOSE 8085

# Define the command to run the application when the container starts
CMD ["java", "-jar", "grocery-booking-api.jar"]
