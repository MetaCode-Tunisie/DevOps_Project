FROM openjdk:11
ARG NEXUS_URL=http://192.168.33.10:8081/repository/maven-releases/tn/esprit/DevOps_Project/2.1/devops-integration.jar
RUN wget -O /devops-integration.jar $NEXUS_URL
CMD ["java", "-jar", "/devops-integration.jar"]
EXPOSE 80