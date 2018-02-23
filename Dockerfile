FROM java:8-jre
MAINTAINER Anton Pavlov <anton.it.pavlov@gmail.com>
ADD ./target/bookshelf.jar /app/
CMD ["java", "-jar", "/app/bookshelf.jar"]
EXPOSE 8080