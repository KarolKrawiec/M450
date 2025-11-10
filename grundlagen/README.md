# Grundlagen

### Aufgabe 1: Formen von Tests in der Informatik (Kurzfassung)

1.  **Unit-Tests (Komponententests):**
    *   **Beschreibung:** Testen isolierte Code-Einheiten (z.B. Methoden), um deren korrekte Funktion sicherzustellen.
    *   **Durchführung:** Entwickler schreiben Tests mit Frameworks (z.B. JUnit), um Eingaben zu prüfen und erwartete Ausgaben zu vergleichen.

2.  **Integrationstests:**
    *   **Beschreibung:** Prüfen das Zusammenspiel mehrerer Komponenten oder Subsysteme und deren Schnittstellen.
    *   **Durchführung:** Zusammengeführte Module werden getestet, oft in einer Testumgebung, die der Produktionsumgebung ähnelt.

3.  **End-to-End-Tests (E2E-Tests):**
    *   **Beschreibung:** Simulieren einen kompletten Benutzer-Workflow durch das gesamte System (UI bis Datenbank).
    *   **Durchführung:** Hochautomatisiert mit Tools (z.B. Selenium), die UI-Interaktionen nachbilden und Gesamtfunktionalität prüfen.

### Aufgabe 2: SW-Fehler, SW-Mangel und hoher Schaden

1.  **SW-Fehler (Bug):**
    *   **Beispiel:** Eine Online-Banking-App zeigt einen Überweisungsbetrag doppelt an, obwohl er nur einmal abgebucht wurde (Anzeigefehler).

2.  **SW-Mangel:**
    *   **Beispiel:** Eine E-Commerce-Plattform erlaubt keine internationalen Versandadressen, obwohl dies in den Anforderungen festgelegt war (fehlende Funktionalität).

3.  **Hoher Schaden durch SW-Fehler:**
    *   **Beispiel:** Der Softwarefehler in der Therac-25 Strahlentherapie-Maschine, der aufgrund einer Race Condition tödliche Überdosierungen verursachte (mehrere Todesfälle und Verletzungen).
