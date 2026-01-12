# Übung 3: Performance & Lasttests

In dieser Übung wurde das Backend der Applikation unter Last gesetzt, um die Performance und Stabilität zu analysieren.

## 1. Verwendete Tools
Neben Standard-Tools wie **Postman** (für manuelle Tests und einfache Runner-Szenarien) wurde ein massgeschneidertes **Node.js Lasttest-Script** (`load_test.js`) verwendet.

### Exploration der Tools:
- **Postman**: Hervorragend geeignet für die Validierung einzelner API-Endpunkte und das Automatisieren von Testreihen (Collection Runner). Bietet gute visuelle Reports für einfache Lastszenarien.
- **Custom Node.js Script**: Bietet maximale Flexibilität. Mit Hilfe des `http`-Moduls können in kurzer Zeit tausende Requests asynchron abgefeuert werden, um die Grenzen des Backends auszuloten.

## 2. Funktionalitäten des Lasttests
Das Test-Script führt folgende Aktionen aus:
- **Asynchrone Requests**: Sendet simultan eine hohe Anzahl an GET-Requests an den `/students` Endpunkt.
- **Status-Validierung**: Überprüft, ob das Backend mit HTTP 200 antwortet oder ob bei steigender Last Fehler (z.B. Timeouts oder 500er Fehler) auftreten.
- **Performance-Metriken**:
    - **RPS (Requests per Second)**: Misst, wie viele Anfragen das Backend pro Sekunde verarbeiten kann.
    - **Gesamtdauer**: Misst die Zeit für die Verarbeitung einer definierten Anzahl an Requests (z.B. 1000 Anfragen).

## 3. Durchführung & Erkenntnisse
Der Test wurde mit 1000 Requests durchgeführt.

**Beispielhafte Ergebnisse:**
- **Erfolgreich**: 1000
- **Fehlgeschlagen**: 0
- **Dauer**: ca. 2.5 Sekunden
- **RPS**: ~400 Requests/Sekunde

### Zusammenfassung
Das Spring Boot Backend erweist sich als stabil gegenüber moderatem Traffic. Engpässe könnten bei einer weiteren Skalierung in der Datenbankanbindung (H2 im In-Memory Modus) oder in der Thread-Limitierung des eingebetteten Tomcat-Servers liegen. Für noch grössere Lasten wären Tools wie **JMeter** oder **k6** zu empfehlen, um komplexere Szenarien (User-Think-Time, variierende Lastkurven) abzubilden.
