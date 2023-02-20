FROM openjdk:17
ADD /target/CurrencyConverter.jar application.jar
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "application.jar"]
