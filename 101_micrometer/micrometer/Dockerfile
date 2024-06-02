# curl endpoints with entrypoint.sh
#FROM azul/zulu-openjdk:17
#
## Install curl
#RUN apt-get update && apt-get install -y curl
#
#VOLUME /tmp
#COPY build/libs/micrometer-0.0.1-SNAPSHOT.jar app.jar
#COPY entrypoint.sh /entrypoint.sh
#RUN chmod +x /entrypoint.sh
#ENTRYPOINT ["/entrypoint.sh"]

# Curl endpoints with java class MetricsDataGenerator
FROM azul/zulu-openjdk:17

# Install curl
RUN apt-get update && apt-get install -y curl

VOLUME /tmp
COPY build/libs/micrometer-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

