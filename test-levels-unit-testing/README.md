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


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Bank Account Simulation Tests")
class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setup() {
        // Fixture: Vor jedem Test ein frisches Konto mit 100.00
        account = new BankAccount(100.0);
    }

    @Test
    @DisplayName("Konstruktor: Positives Startguthaben ist ok")
    void testValidConstructor() {
        assertEquals(100.0, account.getBalance());
    }

    @Test
    @DisplayName("Konstruktor: Negatives Startguthaben wirft Exception")
    void testInvalidConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-50));
    }

    @Test
    @DisplayName("Deposit: Guthaben wird korrekt erhöht")
    void testDeposit() {
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    @DisplayName("Deposit: Negativer Betrag wirft Exception")
    void testNegativeDeposit() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10));
    }

    @Test
    @DisplayName("Withdraw: Guthaben wird korrekt verringert")
    void testWithdraw() {
        account.withdraw(40.0);
        assertEquals(60.0, account.getBalance());
    }

    @Test
    @DisplayName("Withdraw: Zu hohe Abhebung wirft Exception (Überziehung)")
    void testOverdraft() {
        assertThrows(IllegalStateException.class, () -> account.withdraw(200.0));
    }

    @Test
    @DisplayName("Withdraw: Negativer Betrag wirft Exception")
    void testNegativeWithdraw() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-20));
    }
}
```
