FROM openjdk:8
EXPOSE 8082
ADD target/DevOps_Project-2.1.jar devops_project.jar
ENTRYPOINT ["java" , "-jar" ,"devops_project.jar"]