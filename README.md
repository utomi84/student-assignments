# Student Assignments

Ez a projekt egy egyszerű webalkalmazás hallgatói beadandók nyilvántartására, Spring Boot és Thymeleaf segítségével.

## 💡 Funkciók

* Hallgatók nyilvántartása (CRUD)
* Beadandók kezelése (CRUD)
* Kapcsolat hallgatók és beadandók között (1\:N)
* Egységtesztek JUnit-tal
* Kódminőség-ellenőrzés Checkstyle-lal
* Kódfedettség-ellenőrzés Jacoco-val
* CI pipeline GitHub Actions alatt

## ⚙️ Technológiák

* Java 21
* Spring Boot 3.4
* H2 adatbázis
* Thymeleaf
* Maven
* GitHub Actions (CI)
* Checkstyle
* Jacoco

## ⚙️ Build és futtatás

```bash
# Buildelés
./mvnw clean package

# Futás (lokálisan)
./mvnw spring-boot:run
```

## ✅ Tesztek futtatása

```bash
./mvnw test
```

## 🧪 CI a GitHub Actions-ben

Automatikus build és tesztelés minden `master` ágra történő push és PR esetén. A konfigurációt a `.github/workflows/ci.yml` tartalmazza.

---

## 📷 Képernyőképek (opcionális)

*Töltsd fel a későbbiekben a CRUD oldalak képeit.*

---

## 👨‍💻 Készítette

Uri Tamás — Programtervező Informatikus (SVLLC9)
