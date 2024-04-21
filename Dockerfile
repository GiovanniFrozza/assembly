FROM maven:3.8.5-openjdk-17
ENV TZ=America/Sao_Paulo
EXPOSE 8080
WORKDIR /app
COPY pom.xml pom.xml
RUN mvn verify --fail-never
COPY . .
RUN mvn package -DskipTests
ENTRYPOINT ["java","-jar", "/app/target/vote-0.0.1-SNAPSHOT.jar"]
