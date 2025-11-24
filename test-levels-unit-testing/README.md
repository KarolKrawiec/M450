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

# 🧪 JUnit 5 – Zusammenfassung (Aufgabe 2)

Dieses Dokument fasst die gängigsten JUnit-5-Features zusammen.  
Zu jedem Feature gibt es eine kurze Erklärung, typische Anwendungsfälle und ein kleines Codebeispiel.  
Am Ende ist eine Referenzseite verlinkt.

---

## 1. Was ist JUnit?

JUnit ist das Standard-Framework für **automatisierte Unit-Tests** in Java.

**Ziele von Unit-Tests:**

- Verhalten von Klassen und Methoden automatisch prüfen  
- Fehler früh erkennen  
- Refactorings sicherer machen  
- Codequalität verbessern  
- Tests automatisiert in IDE oder über Maven (`mvn test`) ausführen

JUnit 5 besteht aus drei Hauptteilen:

- **JUnit Platform** – Basis zum Ausführen von Tests  
- **JUnit Jupiter API** – Annotations & Assertions (das benutzen wir im Code)  
- **JUnit Jupiter Engine** – eigentliche Test-Engine

---

## 2. Wichtigste JUnit-Annotations

### 2.1 `@Test`

Markiert eine Methode als Testfall.  
Die Methode wird von der Test-Engine ausgeführt.

**Anwendungsfall:**  
Eine einzelne Methode/Unit (z. B. `add()`) testen.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void shouldAddTwoNumbers() {
        Calculator calc = new Calculator();
        double result = calc.add(2.0, 3.0);

        assertEquals(5.0, result);
    }
}
2.2 @BeforeEach

Wird vor jedem Test in der Klasse ausgeführt.
Typisch für Setup, z. B. Testobjekte neu anlegen.

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calc;

    @BeforeEach
    void setUp() {
        // wird vor jedem Testfall ausgeführt
        calc = new Calculator();
    }

    @Test
    void addShouldWork() {
        assertEquals(4.0, calc.add(2.0, 2.0));
    }

    @Test
    void subtractShouldWork() {
        assertEquals(1.0, calc.subtract(3.0, 2.0));
    }
}

2.3 @AfterEach

Wird nach jedem Test ausgeführt.
Kann zum Aufräumen verwendet werden (Ressourcen schließen etc.).

import org.junit.jupiter.api.AfterEach;

class ResourceTest {

    private AutoCloseable resource;

    @BeforeEach
    void setUp() {
        // resource öffnen
    }

    @AfterEach
    void tearDown() throws Exception {
        // wird nach jedem Test ausgeführt
        if (resource != null) {
            resource.close();
        }
    }
}

2.4 @BeforeAll

Wird einmal vor allen Tests in der Testklasse ausgeführt.
Typisch für teure Initialisierung, die nicht pro Test wiederholt werden soll
(z. B. Datenbank starten, Testcontainer).

Wichtig: Methode muss static sein (Standard JUnit Jupiter).

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class IntegrationTest {

    @BeforeAll
    static void globalSetUp() {
        // wird genau einmal ausgeführt, bevor der erste Test startet
        System.out.println("Starte Integrationstests…");
    }

    @Test
    void exampleTest() {
        // …
    }
}

2.5 @AfterAll

Wird einmal nach allen Tests ausgeführt.
Typisch zum globalen Aufräumen (z. B. DB stoppen, Testdaten löschen).

import org.junit.jupiter.api.AfterAll;

class IntegrationTest {

    @AfterAll
    static void globalTearDown() {
        // wird genau einmal ausgeführt, nachdem alle Tests gelaufen sind
        System.out.println("Integrationstests abgeschlossen.");
    }
}

2.6 @DisplayName

Gibt einem Test (oder einer Testklasse) einen lesbaren Namen,
der in der IDE/Maven-Ausgabe angezeigt wird.

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests für den einfachen Calculator")
class CalculatorTest {

    @Test
    @DisplayName("Addition von zwei positiven Zahlen")
    void addTwoPositiveNumbers() {
        Calculator calc = new Calculator();
        assertEquals(7.0, calc.add(3.0, 4.0));
    }
}

2.7 @Disabled

Deaktiviert einen Test oder eine gesamte Testklasse.
Typisch, wenn ein Test noch nicht fertig ist oder temporär nicht laufen soll.

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExperimentalTest {

    @Disabled("Wird später repariert")
    @Test
    void brokenTest() {
        fail("Dieser Test sollte aktuell nicht ausgeführt werden");
    }
}

3. Wichtige Assertions (Assertions-API)

Alle Assertions kommen aus
org.junit.jupiter.api.Assertions.*.

3.1 Gleichheit & Ungleichheit
assertEquals(expected, actual);
assertNotEquals(unexpected, actual);


Beispiel:

@Test
void multiplyShouldReturnCorrectResult() {
    Calculator calc = new Calculator();
    assertEquals(15.0, calc.multiply(3.0, 5.0));
}

3.2 Wahrheitswerte
assertTrue(condition);
assertFalse(condition);


Beispiel:

@Test
void numberShouldBePositive() {
    int value = 5;
    assertTrue(value > 0);
}

3.3 Null / Nicht-Null
assertNull(object);
assertNotNull(object);


Beispiel:

@Test
void findUserShouldReturnNullIfNotFound() {
    UserRepository repo = new UserRepository();
    User user = repo.findById("unknown");

    assertNull(user);
}

3.4 Exceptions mit assertThrows

Prüft, ob eine bestimmte Exception geworfen wird.

Anwendungsfall:
Geschäftslogik verhält sich korrekt im Fehlerfall (z. B. Division durch 0).

@Test
void divideByZeroShouldThrow() {
    Calculator calc = new Calculator();

    IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> calc.divide(10.0, 0.0)
    );

    // Optional: Meldung der Exception prüfen
    assertTrue(ex.getMessage().contains("0"));
}

4. Parameterized Tests (kurzer Überblick)

Mit parameterisierten Tests kann der gleiche Test mit verschiedenen Eingabewerten
automatisch wiederholt werden.

Dafür braucht man zusätzlich (in pom.xml):

<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-params</artifactId>
    <version>${junit.jupiter.version}</version>
    <scope>test</scope>
</dependency>

Beispiel mit @ValueSource
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EvenNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8})
    void shouldBeEven(int number) {
        assertEquals(0, number % 2);
    }
}


Anwendungsfall:

gleiche Logik mit vielen verschiedenen Werten testen

z. B. Eingabewerte validieren, mathematische Funktionen, Parsing etc.

5. Tests mit Maven ausführen

Voraussetzung: Projekt ist ein Maven-Projekt mit JUnit-Abhängigkeit.

Im Projekt-Root (dort, wo pom.xml liegt) ausführen:

mvn test


Maven kompiliert zuerst den Code

Danach werden automatisch alle Tests unter src/test/java ausgeführt

In der Konsole sieht man:

Anzahl Tests

Anzahl Fehler

Ob der Build SUCCESS oder FAILURE ist

6. Referenz / Dokumentation

Offizielle JUnit-5-Dokumentation (User Guide):

https://junit.org/junit5/docs/current/user-guide/

Dort findet man:

Alle verfügbaren Annotations

Details zu Parameterized Tests

Erweiterte Themen (Nested Tests, Test Suites, Tags, Conditionals etc.)

Konfiguration für Maven, Gradle und IDEs
