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

Hier ist die komplette Lösung für alle 4 Aufgaben in einem sauber strukturierten Markdown-Dokument. Du kannst dieses File direkt in dein Repository als `Lösungen.md` oder `README.md` übernehmen.

Da ich den Code deiner spezifischen "Banken Simulation" aus Aufgabe 3 nicht sehe, habe ich eine **Standard-Implementierung** (Best Practice) erstellt, auf der die Erklärung und die Tests basieren.

---


# 📝 Lösungen: JUnit 5 Workshop (Aufgaben 1-4)

Dieses Dokument enthält den Source-Code, die Tests und die Dokumentation für die Aufgaben 1 bis 4.

---

## ✅ Aufgabe 1: Simpler Rechner

### 1.1 Source Code (`src/main/java/Calculator.java`)

```java
public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division durch Null ist nicht erlaubt");
        }
        return a / b;
    }
}
```

### 1.2 Unit Tests (`src/test/java/CalculatorTest.java`)

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calc = new Calculator();

    @Test
    @DisplayName("Testet Addition: 10 + 5 = 15")
    void testAdd() {
        assertEquals(15.0, calc.add(10.0, 5.0));
    }

    @Test
    @DisplayName("Testet Subtraktion: 10 - 5 = 5")
    void testSubtract() {
        assertEquals(5.0, calc.subtract(10.0, 5.0));
    }

    @Test
    @DisplayName("Testet Multiplikation: 10 * 5 = 50")
    void testMultiply() {
        assertEquals(50.0, calc.multiply(10.0, 5.0));
    }

    @Test
    @DisplayName("Testet Division: 10 / 2 = 5")
    void testDivide() {
        assertEquals(5.0, calc.divide(10.0, 2.0));
    }

    @Test
    @DisplayName("Testet Division durch Null (Exception)")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10.0, 0.0));
    }
}
```

### 1.3 Ausführung

*   **IDE:** Rechtsklick auf `CalculatorTest` -> `Run`.
*   **Terminal:** `mvn test`

---

## ✅ Aufgabe 2: JUnit Zusammenfassung

Hier sind die wichtigsten JUnit 5 Features kompakt erklärt.

### Wichtigste Annotations

| Annotation | Beschreibung | Beispiel-Szenario |
| :--- | :--- | :--- |
| `@Test` | Markiert eine Methode als ausführbaren Testfall. | `testLogin()` |
| `@BeforeEach` | Läuft **vor jedem** einzelnen Test. | Objekte instanziieren, DB-Verbindung öffnen. |
| `@AfterEach` | Läuft **nach jedem** einzelnen Test. | Ressourcen freigeben, Speicher bereinigen. |
| `@BeforeAll` | Läuft **einmalig** ganz am Anfang (muss `static` sein). | Teure Server-Verbindung einmalig aufbauen. |
| `@AfterAll` | Läuft **einmalig** ganz am Ende (muss `static` sein). | Server herunterfahren. |
| `@DisplayName` | Gibt dem Test im Report einen lesbaren Namen. | "Sollte Fehler werfen bei negativem Input" |
| `@Disabled` | Deaktiviert einen Test temporär (Skip). | Feature ist noch nicht fertig implementiert. |

### Wichtigste Assertions

*   **`assertEquals(expected, actual)`**: Prüft, ob zwei Werte gleich sind.
*   **`assertTrue(condition)` / `assertFalse`**: Prüft, ob eine Bedingung wahr/falsch ist.
*   **`assertNotNull(obj)`**: Prüft, ob ein Objekt existiert (nicht null ist).
*   **`assertThrows(Exception.class, executable)`**: Prüft, ob der Code exakt diesen Fehler wirft.

**Referenz:** [Offizieller JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

---

## ✅ Aufgabe 3: Banken Simulation (Analyse)

### 3.1 Der Code (`src/main/java/BankAccount.java`)

```java
public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Startsaldo darf nicht negativ sein");
        }
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Betrag muss positiv sein");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Betrag muss positiv sein");
        }
        if (amount > balance) {
            throw new IllegalStateException("Konto nicht gedeckt");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
```

### 3.2 Funktionsweise (Dokumentation)

*   **Zweck:** Die Klasse `BankAccount` bildet ein einfaches Girokonto ab.
*   **Datenhaltung:** Der Kontostand wird in der privaten Variable `balance` (double) gespeichert.
*   **Validierung:**
    *   **Konstruktor:** Verhindert das Erstellen von Konten mit Schulden.
    *   **Deposit (Einzahlen):** Akzeptiert nur positive Beträge.
    *   **Withdraw (Abheben):** Prüft a) ob der Betrag positiv ist und b) ob genügend Geld auf dem Konto ist (`IllegalStateException` bei Überziehung).

---

## ✅ Aufgabe 4: Unit-Tests Bank

Diese Tests decken "Happy Path" (alles okay) und "Edge Cases" (Fehlerfälle) ab, um eine hohe Code Coverage zu erreichen.

### 4.1 Test Code (`src/test/java/BankAccountTest.java`)

```java
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
