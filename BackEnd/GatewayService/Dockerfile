FROM openjdk:17-alpine

LABEL author="Juan Valverde"

WORKDIR /app

COPY target/gateWay-0.0.1-SNAPSHOT.jar sophos-uni-gateway-ms.jar

EXPOSE 80

ENTRYPOINT ["java", "-jar", "sophos-uni-gateway-ms.jar"]
