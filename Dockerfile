#naming first stage as "build"
FROM maven:3.9.12-eclipse-temurin-11 AS build

EXPOSE 8080

WORKDIR /SortingMadness

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:11.0.16-jdk

WORKDIR /SortingMadness
#Coping jar files from the previous stage
COPY --from=build ./SortingMadness/target/*.jar /SortingMadness/SortingMadnessApp.jar

CMD ["java", "-jar","./SortingMadnessApp.jar"]