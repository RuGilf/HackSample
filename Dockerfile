FROM eclipse-temurin:25-jre AS build

WORKDIR /HackSample

COPY . .

RUN chmod +x gradlew
RUN ./gradlew buildFatJar --no-daemon

FROM eclipse-temurin:25-jre AS runtime

WORKDIR /HackSample

COPY --from=build /HackSample/build/libs/*-all.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "HackSample.jar"]