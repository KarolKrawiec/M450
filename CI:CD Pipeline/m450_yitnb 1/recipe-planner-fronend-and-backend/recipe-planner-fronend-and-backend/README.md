# M450 - Recipe Planner Solution

Dieses Repository enthält die Lösungen für die M450 Aufgaben (Frontend & Backend).

## Übersicht der Lösungen

### Aufgabe 1: Unit Testing
Die Unit Tests befinden sich im Backend-Ordner unter `src/test/java`.
- **Controller Tests:** `ControllerTests/RecipeControllerTest.java` testet die API-Endpunkte mittels `MockMvc`.
- **Mapper Tests:** `MapperTests/RecipeEntityMapperTest.java` testet das Mapping mittels `SoftAssertions` (AssertJ).

### Aufgabe 2: Reports
Test-Reports werden automatisch generiert.
- **Ausführung:** Führen Sie das Skript `./build_pipeline.sh` aus.
- **Report-Ort:** Nach Ausführung zu finden unter `recipe-planner-backend/target/site/jacoco/index.html`.

### Aufgabe 3: Pipeline
Die CI/CD Pipeline wurde für GitLab definiert.
- **Datei:** `.gitlab-ci.yml` im Backend-Verzeichnis.
- **Anpassung:** Das Image wurde auf `maven:3.9.6-eclipse-temurin-21` aktualisiert, um Kompatibilität mit moderneren Java-Versionen zu gewährleisten.

### Deployment Aufgaben (Aufgabe 1 & 2)
Eine detaillierte Dokumentation zu den Deployment-Umgebungen und meiner Entscheidung für Docker Compose finden Sie in der Datei:
📄 **[DEPLOYMENT.md](DEPLOYMENT.md)**

Die praktische Umsetzung (Docker Setup) finden Sie in:
- `docker-compose.yml`
- `recipe-planner-backend/Dockerfile`
- `recipe-planner-fronend/Dockerfile`

---

## Starten der Applikation

Sie haben drei Möglichkeiten, die Applikation zu starten:

### 1. Via Helper Scripts (Empfohlen für Mac)
Startet Backend und Frontend lokal.
```bash
# Terminal 1
./start_backend.sh

# Terminal 2
./start_frontend.sh
```

### 2. Via Build Pipeline (Tests & Reports)
Führt Clean Build, Tests und Report-Generierung durch.
```bash
./build_pipeline.sh
```

### 3. Via Docker Compose (Containerisiert)
Startet die gesamte Umgebung (Frontend + Backend) in Containern.
```bash
docker-compose up --build
```

---

## Projektstruktur
- `recipe-planner-backend/`: Spring Boot Applikation
- `recipe-planner-fronend/`: React Applikation
- `.gitlab-ci.yml`: CI/CD Konfiguration
