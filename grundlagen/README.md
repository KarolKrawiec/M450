# Grundlagen

### Aufgabe 1: Formen von Tests in der Informatik

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

* Java file

```
public class PriceCalculator {

    public double calculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount) {
        double addon_discount_percentage;

        if (extras >= 5) {
            addon_discount_percentage = 15;
        } else if (extras >= 3) {
            addon_discount_percentage = 10;
        } else {
            addon_discount_percentage = 0;
        }

        double price_after_base_discount = baseprice * (100 - discount) / 100.0;
        double price_after_extra_discount = extraprice * (100 - addon_discount_percentage) / 100.0;
        double total_result = price_after_base_discount + specialprice + price_after_extra_discount;

        return total_result;
    }
}
```

* Test file

```
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

* Resultat

```
--- Starte Tests für calculatePrice-Methode ---
✅ Test 1 OK: (Erwartet: 100.00, Tatsächlich: 100.00)
✅ Test 2 OK: (Erwartet: 90.00, Tatsächlich: 90.00)
✅ Test 3 OK: (Erwartet: 190.00, Tatsächlich: 190.00)
✅ Test 4 OK: (Erwartet: 185.00, Tatsächlich: 185.00)
✅ Test 5 OK: (Erwartet: 180.00, Tatsächlich: 180.00)
✅ Test 6 OK: (Erwartet: 180.00, Tatsächlich: 180.00)
✅ Test 7 OK: (Erwartet: 330.00, Tatsächlich: 330.00)
✅ Test 8 OK: (Erwartet: 200.00, Tatsächlich: 200.00)

--- Testergebnisse Zusammenfassung ---
✅ Alle Tests erfolgreich!
```
