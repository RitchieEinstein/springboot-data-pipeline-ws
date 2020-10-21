#FROM adoptopenjdk/maven-openjdk11:latest
#VOLUME /tmp
#EXPOSE 12000
#COPY * /usr/proj/
#RUN ls -la /usr/proj
#WORKDIR /usr/proj
#CMD mvn clean package
##ADD target/*.jar app.jar
#
##ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]

FROM adoptopenjdk/openjdk11:ubi
VOLUME /tmp
EXPOSE 12000
ADD target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]