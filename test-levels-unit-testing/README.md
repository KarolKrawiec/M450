## Aufgabe 1: Diskussion über das Testen in der Firma

### Was wird wie in unserer Firma getestet?
- Software-Anwendungen (Web, Mobile, Backend).
- Manuelle und automatisierte Tests (Unit, Integration, E2E).
- Tools: Cypress, JUnit/Mockito, Postman.

### Mit welchen Test Levels hatten wir bereits zu tun?
- **Unit Testing:** Entwickler.
- **Integration Testing:** Entwickler (White-Box) & QA-Team (Black-Box).
- **System Testing:** QA-Team vor Releases.
- **Acceptance Testing (UAT):** Key-User/Business vor Go-Live.

### Wann werden Tests ausgeführt?
- Automatisierte Tests: Bei jedem Code-Commit (CI/CD).
- Manuelle Tests: In Testzyklen vor Releases/Sprint-Enden.
- UAT: Vor Deployment.

### Haben wir dedizierte Testing oder QA Teams?
- Ja, dediziertes QA-Team für Integration, System, UAT-Teile.
- Entwickler sind für Unit- und Teile der Integrationstests zuständig.
- "Whole Team Approach" (Qualität = Verantwortung aller).

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
