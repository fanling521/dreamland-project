FROM frolvlad/alpine-java:jdk8-slim
VOLUME /tmp
ADD /target/dreamland-attachment-api-7006.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom ","Duser.timezone=GMT+08","-jar","/app.jar","--spring.profiles.active=prod"]