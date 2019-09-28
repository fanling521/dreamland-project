FROM frolvlad/alpine-java:jdk8-slim
VOLUME /tmp
ADD /target/dreamland-auth-api-7003.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom ","-jar","/app.jar","--spring.profiles.active=prod"]