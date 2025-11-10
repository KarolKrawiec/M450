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

### Aufgabe 3: Test in der Klasse Preisberechnung

* Test file

```
// PriceCalculatorTest.java
public class PriceCalculatorTest {

    public static void main(String[] args) {
        runAllTests();
    }

    public static boolean runAllTests() {
        PriceCalculator calculator = new PriceCalculator(); // <-- Diese Klasse muss in PriceCalculator.java sein
        boolean allTestsPassed = true;

        System.out.println("--- Starte Tests für calculatePrice-Methode ---");

        allTestsPassed &= runSingleTest(calculator, "Test 1", 100, 0, 0, 0, 0, 100.0);
        allTestsPassed &= runSingleTest(calculator, "Test 2", 100, 0, 0, 0, 10, 90.0);
        allTestsPassed &= runSingleTest(calculator, "Test 3", 100, 0, 100, 3, 0, 190.0);
        allTestsPassed &= runSingleTest(calculator, "Test 4", 100, 0, 100, 5, 0, 185.0);
        allTestsPassed &= runSingleTest(calculator, "Test 5", 100, 0, 100, 3, 10, 180.0);
        allTestsPassed &= runSingleTest(calculator, "Test 6", 100, 0, 100, 5, 5, 180.0);
        allTestsPassed &= runSingleTest(calculator, "Test 7", 200, 50, 100, 4, 5, 330.0);
        allTestsPassed &= runSingleTest(calculator, "Test 8", 100, 0, 100, 2, 0, 200.0);

        System.out.println("\n--- Testergebnisse Zusammenfassung ---");
        if (allTestsPassed) {
            System.out.println("✅ Alle Tests erfolgreich!");
        } else {
            System.err.println("❌ Einige Tests sind fehlgeschlagen!");
        }
        return allTestsPassed;
    }

    private static boolean runSingleTest(PriceCalculator calculator, String testName,
                                         double baseprice, double specialprice, double extraprice,
                                         int extras, double discount, double expectedResult) {
        double actualResult = calculator.calculatePrice(baseprice, specialprice, extraprice, extras, discount);
        if (Math.abs(actualResult - expectedResult) < 0.001) {
            System.out.printf("✅ %s OK: (Erwartet: %.2f, Tatsächlich: %.2f)%n", testName, expectedResult, actualResult);
            return true;
        } else {
            System.err.printf("❌ %s FEHLGESCHLAGEN: (Erwartet: %.2f, Tatsächlich: %.2f)%n", testName, expectedResult, actualResult);
            return false;
        }
    }
}
```
