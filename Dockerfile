FROM openjdk:11-jre-slim
EXPOSE 8089
WORKDIR /app
ADD target/achat-1.2.0.jar achat-1.2.0.jar
ENTRYPOINT ["java","-jar","/achat-1.2.0.jar"]