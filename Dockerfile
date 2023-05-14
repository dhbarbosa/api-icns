FROM adoptopenjdk:17-jdk-hotspot

WORKDIR /app

COPY target/my-spring-app.jar .

ENV BD= \
  USERBANCO= \
  PASSWORD= \
  PORT=8080 \
  API_ISSUER= \
  API_SECRET=

EXPOSE $PORT

CMD ["java", "-jar", "my-spring-app.jar"]
