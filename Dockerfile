# -------- Etapa de compilación (usa JDK Debian y apt para instalar ant) --------
FROM eclipse-temurin:17-jdk AS build

# instalar ant (Debian-based)
RUN apt-get update && apt-get install -y ant && rm -rf /var/lib/apt/lists/*

WORKDIR /src

# Copiamos todo (si tienes archivos grandes o secretos, usa .dockerignore)
COPY . .

# Ejecutamos ant. Ajusta el target si tu build.xml usa otro target (ej: "jar")
# Si falla con 'ant' el comando seguirá devolviendo error: ese error se verá aquí.
RUN ant -f build.xml || ant

# Buscar el primer JAR generado y moverlo a /out/app.jar (ruta conocida para la etapa final)
RUN mkdir -p /out && \
    jarfile=$(find . -type f -name "*.jar" | grep -v "/nbproject/" | head -n 1) && \
    if [ -z "$jarfile" ]; then echo "ERROR: No se encontró ningún .jar después de ant"; exit 1; fi && \
    cp "$jarfile" /out/app.jar

# -------- Etapa runtime - imagen más ligera --------
FROM eclipse-temurin:17-jdk-alpine AS runtime
WORKDIR /app

# Copiamos el jar ya conocido
COPY --from=build /out/app.jar ./app.jar

# Expone el puerto 8080 por defecto (ajusta si tu app usa otro puerto)
EXPOSE 8080

# Ejecutar la app
CMD ["java", "-jar", "app.jar"]
