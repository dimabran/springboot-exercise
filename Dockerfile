FROM openjdk:15
ADD target/springboot-exercise.jar springboot-exercise.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar", "springboot-exercise.jar"]
