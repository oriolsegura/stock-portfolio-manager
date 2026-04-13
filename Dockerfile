FROM eclipse-temurin:25-jdk-noble AS build
ARG MAVEN_VERSION=4.0.0-rc-5
WORKDIR /app
RUN MAVEN_MAJOR=$(echo ${MAVEN_VERSION} | cut -d'.' -f1) \
    && apt-get update && apt-get install -y curl tar \
    && curl -fsSL https://archive.apache.org/dist/maven/maven-${MAVEN_MAJOR}/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz -o maven.tar.gz \
    && tar -xzf maven.tar.gz -C /opt \
    && ln -s /opt/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/bin/mvn \
    && rm maven.tar.gz
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jre-noble
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
