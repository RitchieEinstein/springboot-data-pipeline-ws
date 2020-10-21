FROM adoptopenjdk/openjdk11:ubi
VOLUME /tmp
EXPOSE 8761
ADD target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]