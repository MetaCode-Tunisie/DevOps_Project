

FROM openjdk:11
EXPOSE 8082
ENV NEXUS_URL="http://192.168.33.10:8081"
ENV JAR_FILE_PATH="/repository/maven-releases/tn/esprit/DevOps_Project/2.1/DevOps_Project-2.1.jar"
RUN curl -o devops_project.jar "${NEXUS_URL}${JAR_FILE_PATH}"
ENTRYPOINT ["java", "-jar", "DevOps_Project-2.1.jar"]
	
