# Utiliza una imagen base de OpenJDK para Java (puedes cambiar la versión según tus necesidades)
FROM openjdk:17

# Copia los archivos necesarios del proyecto al contenedor
COPY . /app

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Otorgar permisos al script
RUN chmod +x mvnw

# Expone el puerto en el que la aplicación escucha (ajusta según las necesidades de tu aplicación)
RUN ./mvnw clean install -DskipTests

# Comando para ejecutar la aplicación cuando se inicia el contenedor
CMD ["java", "-jar", "target/proj-0.0.1-SNAPSHOT.jar"]
