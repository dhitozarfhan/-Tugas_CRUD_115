# Tugas_CRUD_NIM

Aplikasi CRUD untuk data KTP dibangun menggunakan **Spring Boot (REST API)** di sisi backend dan **HTML, CSS, JQuery (Ajax)** di sisi frontend. Proyek ini mendemonstrasikan integrasi antara Java backend dengan interface web modern yang responsif.

## Fitur Utama
- **CRUD Operations**: Create, Read, Update, dan Delete data KTP.
- **Real-time API**: Komunikasi Ajax yang cepat tanpa reload halaman.
- **Input Validation**: Validasi data baik di sisi client maupun server.
- **Responsive Design**: Antarmuka yang optimal di berbagai ukuran layar.

## Persyaratan Sistem
- Java 17
- Maven
- MySQL Database

## Konfigurasi Database
1. Buat database di MySQL dengan nama `spring`.
2. Pastikan credentials di file `.env` sudah sesuai dengan environment lokal Anda. File `.env` diletakkan sejajar dengan `pom.xml`.
   ```env
   DB_URL=jdbc:mysql://localhost:3306/spring?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
   DB_USERNAME=root
   DB_PASSWORD=password_anda
   ```

## Cara Menjalankan Aplikasi
1. Buka terminal di folder root proyek `Tugas_CRUD_NIM`.
2. Jalankan perintah berikut untuk meng-compile dan menjalankan server:
   ```bash
   .\mvnw spring-boot:run
   ```
3. Setelah server berjalan (muncul tulisan `Started KtpApplication`), buka browser dan akses alamat berikut untuk melihat tampilan UI:
   ```
   http://localhost:8080/index.html
   ```

## Endpoint REST API Dokumentasi
Aplikasi ini menyediakan REST API di endpoint root `/api/ktp`:

* **GET /api/ktp**
  Digunakan untuk mengambil semua daftar data KTP.
* **GET /api/ktp/{id}**
  Digunakan untuk mengambil data KTP secara spesifik berdasarkan ID.
* **POST /api/ktp**
  Digunakan untuk merekam data KTP baru ke dalam database. Menerima payload JSON Body.
* **PUT /api/ktp/{id}**
  Digunakan untuk memperbarui data KTP yang ada berdasarkan ID. Menerima payload JSON Body.
* **DELETE /api/ktp/{id}**
  Digunakan untuk menghapus data KTP berdasarkan ID.

## Screenshot UI
*(Silakan tambahkan file screenshot tampilan antarmuka web ke folder repository dan letakkan link path-nya di sini)*
- ![Screenshot Halaman Awal]() 

---
**Catatan:** Proyek ini dikerjakan untuk memenuhi evaluasi Tugas CRUD KTP.

![alt text](docs/Screenshot%202026-03-10%20105029.png) 
![alt text](docs/Screenshot%202026-03-10%20105115.png) 
![alt text](docs/Screenshot%202026-03-10%20105121.png) 
![alt text](docs/Screenshot%202026-03-10%20105133.png) 
![alt text](docs/Screenshot%202026-03-10%20105140.png)
![alt text](docs/Screenshot%202026-03-10%20110156.png)
