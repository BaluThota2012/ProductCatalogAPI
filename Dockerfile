FROM openjdk:8u302-jdk-bullseye
WORKDIR /app
COPY ./target/product-catalog.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Xms512m","-Xmx512m", "-XX:+HeapDumpOnOutOfMemoryError","-XX:HeapDumpPath=/app/logs/product-catalog-heap.hprof","-jar","app.jar"]