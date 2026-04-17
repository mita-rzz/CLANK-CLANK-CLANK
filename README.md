# POS-BENGKEL
Semua ini digunakan untuk memenuhi tugas UTS PBO


# Arsitektur

Proyek ini menggunakan arsitektur MVC yang sudah dimodifikasi dan disesuaikan dengan kebutuhan serta bahasa pemrograman yang digunakan. 

# HOW TO ??:
1. Melakukan git pull
2. Menjalankan projek java dengan menggunakan syntax dibawah ini:


Syntax dibawah ini untuk membuat file jar yang akan digunakan untuk menciptakan .exe
```bash
   mvn clean package
```
```bash
    java -jar target/pos-bengkel-1.0-SNAPSHOT-jar-with-dependencies.jar
```

syntax dibawah ini  untuk langsung menjalankan tanpa menciptakan file .jar
```bash
mvn compile exec:java -Dexec.mainClass="Main"
```