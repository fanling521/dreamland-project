FROM frolvlad/alpine-java:jdk8-slim
VOLUME /tmp
ADD /target/dreamland-eureka-7000.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom ","-jar","/app.jar","--spring.profiles.active=prod"]