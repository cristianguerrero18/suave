FROM eclipse-temurin:17.0.13_11-jdk

# Informar el puerto donde se ejecuta
EXPOSE 8080

WORKDIR /root

# Copiar archivos necesarios para construir
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

# Descargar dependencias en modo offline
RUN ./mvnw dependency:go-offline

# Copiar el código fuente
COPY ./src /root/src

# Compilar la aplicación
RUN ./mvnw clean install -DskipTests

# Configurar el punto de entrada
ENTRYPOINT ["java","-jar","/root/target/prueba-0.0.1-SNAPSHOT.jar"]



