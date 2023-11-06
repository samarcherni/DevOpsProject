FROM openjdk:11
EXPOSE 8089
# Mettez à jour le système et installez curl pour télécharger le JAR
RUN apt-get update && apt-get install -y curl
RUN curl -o timesheet.jar "http://http://192.168.33.10:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar"
ENTRYPOINT ["java", "-jar", "timesheet.jar"]
