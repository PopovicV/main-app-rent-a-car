FROM openjdk:8-jdk-alpine
COPY entrypoint.sh /entrypoint.sh
COPY target/request-0.0.1-SNAPSHOT.jar request-0.0.1-SNAPSHOT.jar
RUN chmod +x /entrypoint.sh

# Add docker-compose-wait tool -------------------
ENV WAIT_VERSION 2.7.2
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait
RUN chmod +x /wait

CMD ["/entrypoint.sh"]
