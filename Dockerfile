FROM openjdk:11
ARG NEXUS_URL=http://192.168.33.10:8081/repository/maven-releases/tn/esprit/DevOps_Project/2.2/DevOps_Project-2.2.jar
RUN wget -O /DevOps_Project-2.2.jar $NEXUS_URL
CMD ["java", "-jar", "/DevOps_Project-2.2.jar"]
EXPOSE 8082