
FROM openjdk:17-jdk as builder

WORKDIR application

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Copy the project source
COPY src src

# Package the application
RUN ./mvnw package -DskipTests

# Use Tomcat image as the base image
FROM tomcat:9-jdk17-openjdk

# Remove the default Tomcat applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file to the Tomcat applications directory
COPY --from=builder /application/target/currency-0.0.2.war /usr/local/tomcat/webapps/ROOT.war

# Expose the Tomcat port
EXPOSE 8082

