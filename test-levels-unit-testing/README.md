## Aufgabe 1: Diskussion über das Testen in der Firma

### Was wird wie in unserer Firma getestet?
- Software-Anwendungen (Web, Mobile, Backend).
- Manuelle und automatisierte Tests (Unit, Integration, E2E).
- Tools: Cypress, JUnit/Mockito, Postman.

### Mit welchen Test Levels hatten wir bereits zu tun?
- **Unit Testing:** Entwickler.
- **Integration Testing:** Entwickler (White-Box) & QA-Team (Black-Box).

### Wann werden Tests ausgeführt?
- Automatisierte Tests: Bei jedem Code-Commit (CI/CD).
- Manuelle Tests: In Testzyklen vor Releases/Sprint-Enden.

### Haben wir dedizierte Testing oder QA Teams?
- Ja, dediziertes QA-Team für Integration, System, UAT-Teile.
- Entwickler sind für Unit- und Teile der Integrationstests zuständig.

### Wie sieht unser Testing Life Cycle aus?
- Planung (basierend auf Stories), Testfallerstellung.
- Automatisierte Tests in CI/CD.
- Manuelle Testausführung in Phasen.
- Defect Management, Testberichte.
- Eng in agile Sprints integriert.

---

## Aufgabe 2: Begriffe einordnen und Abhängigkeiten erkennen

### Testing approach (Testansatz)
- **Erklärung:** Gesamtstrategie/Plan für das Testen (z.B. risikobasiert, agil).
- **Abhängigkeit:** Leitfaden für alle anderen Testaktivitäten, bestimmt Auswahl und Priorisierung.

### Testing levels (Teststufen)
- **Erklärung:** Verschiedene Phasen des Testens mit unterschiedlichem Fokus (Unit, Integration, System, Acceptance).
- **Abhängigkeit:** Sind hierarchisch angeordnet und definieren, *was* zu welchem Zeitpunkt getestet wird, als Teil des Testansatzes.

### Testing types, techniques and tactics (Testarten, -techniken und -taktiken)

#### Testing Types (Testarten)
- **Erklärung:** Kategorien von Tests nach Ziel/Aspekt (funktional, nicht-funktional wie Performance, Sicherheit, Regression).
- **Abhängigkeit:** Werden auf den verschiedenen Test Levels angewendet.

#### Testing Techniques (Testtechniken)
- **Erklärung:** Methoden zum Entwerfen von Testfällen (Äquivalenzklassen, Grenzwertanalyse, Exploratives Testen).
- **Abhängigkeit:** Werden genutzt, um Testfälle für die verschiedenen Testarten zu erstellen.

#### Testing Tactics (Testtaktiken)
- **Erklärung:** Praktische Aktionen/Herangehensweisen bei der Testausführung (Testautomatisierung, Risikobasierte Priorisierung, Mocking).
- **Abhängigkeit:** Konkrete Schritte zur effizienten Durchführung der Testarten, beeinflusst durch den Testansatz und die Techniken.

---

**Zusammenfassende Abhängigkeit:** Der **Testansatz** führt durch die **Test Levels**, wo **Testarten** angewendet werden. **Testtechniken** helfen bei der Testfallerstellung, und **Testtaktiken** bei der effizienten Ausführung.


# JUnit Aufgaben – Java/Maven Projekt

Dieses Repository enthält die Lösungen zu den vier vorgegebenen JUnit-Aufgaben.  
Alle Arbeiten wurden in einem Maven-Projekt umgesetzt und gemäss Aufgabenstellung dokumentiert und getestet.

---

## 🧩 Aufgabe 1 – Simpler Rechner

**Ziel:** Einstieg in JUnit 5, Erstellung einer eigenen Klasse sowie dazugehörigen Unit-Tests.

**Umsetzung enthalten im Repository:**

| Art | Datei | Inhalt |
|------|--------|----------|
| Klasse (Main) | `src/main/java/.../Calculator.java` | Methoden: `add`, `subtract`, `multiply`, `divide` |
| Testklasse | `src/test/java/.../CalculatorTest.java` | Verschiedene Testfälle pro Methode inkl. Division-by-Zero-Exception |

**Testanforderungen erfüllt:**

- Alle vier Methoden implementiert (`+`, `−`, `*`, `/`)
- Verschiedene Testfälle (positive, negative, Randfälle)
- Testausführung über IDE **und** Maven (`mvn test`)
- Nutzung von JUnit 5 Assertions und Annotations

---

## 🧩 Aufgabe 2 – JUnit Zusammenfassung

**Ziel:** Wichtigste Grundlagen von JUnit dokumentieren.

**Dokumentation:**  
➡️ `docs/junit-zusammenfassung.md`

Enthält:

- Erklärung was JUnit und Unit-Testing sind
- Wichtige JUnit 5 Annotations (`@Test`, `@BeforeEach`, `@AfterAll`, usw.)
- Beispielsnippets
- Assertions-Übersicht
- Referenz-Link zur offiziellen Dokumentation

---

## 🧩 Aufgabe 3 – Banken-Simulation (Verstehen & Dokumentieren)

**Ziel:** Vorhandene Bank-Simulation analysieren und Verständnis dokumentieren.

**Dokumentation:**  
➡️ `docs/banken-simulation-notizen.md`

Inhalt:

- Übersicht über Software-Ziel
- Auflistung der relevanten Klassen
- Beschreibung der Beziehungen (z. B. Konto ↔ Bank ↔ Transaktion)
- Grundlegender Ablauf anhand typischer Schritte

---

## 🧩 Aufgabe 4 – Unit-Tests für Banken-Simulation

**Ziel:** Software-Logik mit Unit-Tests abdecken und sinnvolle Coverage erreichen.

**Im Repository enthalten:**  
Unter `src/test/java/.../bank/...`

Getestete Bereiche z. B.:

- Kontofunktionen (`deposit`, `withdraw`)
- Fehlerfälle (z. B. negative Beträge, zu geringe Mittel)
- Bank-Operationen (`transfer` Validität & Ergebnis)
- Exceptions / Sonderfälle

Ziel war **funktionale Kernlogik sinnvoll abzudecken**, nicht 100% Coverage um jeden Preis.

---

## 🧪 Tests ausführen

### IDE

Rechtsklick → `Run Tests`  
oder Testklasse auswählen

### Maven (Konsole)

```bash
mvn test
