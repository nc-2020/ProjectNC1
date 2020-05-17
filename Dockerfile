FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY backend/target/brain-duel-app.war brain-duel-app.war
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/brain-duel-app.war"]