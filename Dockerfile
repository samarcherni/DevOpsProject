FROM openjdk:11
EXPOSE 8085
WORKDIR /app
RUN apt-get update && apt-get install -y curl
RUN curl -o achat.jar -L "http://192.168.56.5:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar"
ENTRYPOINT ["java", "-jar", "achat.jar"]
