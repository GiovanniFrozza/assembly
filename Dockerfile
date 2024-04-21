FROM maven:3.8.5-openjdk-17
CMD ["mvn","package"]
#COPY ./target/vote-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java","-jar","vote-0.0.1-SNAPSHOT.jar"]
