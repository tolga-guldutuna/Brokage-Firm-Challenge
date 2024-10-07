# Base image olarak resmi Java 17 image'ı kullanıyoruz
FROM openjdk:17-jdk-alpine

# Proje jar dosyasının adı
ARG JAR_FILE=target/Brokage-Firm-Challenge-v0.0.1.jar

# Uygulama için çalıştırılacak dizini oluştur
WORKDIR /app

# Jar dosyasını oluşturulan dizine kopyala
COPY ${JAR_FILE} app.jar

# Gerekli portları expose ediyoruz
EXPOSE 8585

# Uygulamayı çalıştırmak için entrypoint ayarlıyoruz
ENTRYPOINT ["java","-jar","/app/app.jar"]
