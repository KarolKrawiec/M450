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

**Ziel:** Die gegebene `calculatePrice`-Methode in deiner Entwicklungsumgebung umsetzen und einen Testtreiber erstellen, um die Preisberechnung zu testen.

**Die `calculatePrice`-Methode (bereits mit den Korrekturen aus dem Bonus):**

```java
public class PriceCalculator {

    /**
     * Berechnet den Endpreis eines Artikels unter Berücksichtigung von Grundpreis,
     * Sondermodellaufschlag, Zusatzausstattungen und Rabatten.
     *
     * @param baseprice      Der Grundpreis des Artikels.
     * @param specialprice   Der Aufschlag für ein Sondermodell.
     * @param extraprice     Der Gesamtpreis der Zusatzausstattungen.
     * @param extras         Die Anzahl der ausgewählten Zusatzausstattungen.
     * @param discount       Der Händlerrabatt in Prozent, bezogen auf den Grundpreis.
     * @return Der berechnete Endpreis.
     */
    public double calculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount) {
        double addon_discount_percentage; // Rabattprozentsatz für Zusatzausstattungen

        // Bestimmung des Zubehörrabatts basierend auf der Anzahl der Extras
        // Wichtig: Reihenfolge der if-Bedingungen, spezifischere zuerst!
        if (extras >= 5) {
            addon_discount_percentage = 15; // 15% Rabatt bei 5 oder mehr Extras
        } else if (extras >= 3) {
            addon_discount_percentage = 10; // 10% Rabatt bei 3 oder mehr (aber weniger als 5) Extras
        } else {
            addon_discount_percentage = 0;  // Kein Rabatt bei weniger als 3 Extras
        }

        // Berechnung des Grundpreises nach Abzug des Händlerrabatts
        // Der Händlerrabatt bezieht sich NUR auf den Grundpreis.
        double price_after_base_discount = baseprice * (100 - discount) / 100.0;

        // Berechnung des Zubehörpreises nach Abzug des Zubehörrabatts
        // Der Zubehörrabatt bezieht sich NUR auf den Preis der Zusatzausstattungen.
        double price_after_extra_discount = extraprice * (100 - addon_discount_percentage) / 100.0;

        // Der Gesamtpreis ist die Summe aus rabattiertem Grundpreis, Sondermodellaufschlag
        // und rabattiertem Zubehörpreis.
        double total_result = price_after_base_discount + specialprice + price_after_extra_discount;

        return total_result;
    }
}
```

**Erstellen eines Testtreibers (`PriceCalculatorTest.java`):**

Der Testtreiber ist ein einfaches Programm, das die `calculatePrice`-Methode mit verschiedenen Parametern aufruft und die Ergebnisse mit den erwarteten Werten vergleicht.

```java
public class PriceCalculatorTest {

    public static void main(String[] args) {
        // Starte alle definierten Testfälle
        runAllTests();
    }

    /**
     * Führt eine Reihe von Testfällen für die calculatePrice-Methode aus.
     * Gibt true zurück, wenn alle Tests erfolgreich sind, sonst false.
     */
    public static boolean runAllTests() {
        PriceCalculator calculator = new PriceCalculator();
        boolean allTestsPassed = true; // Flag, um den Gesamtstatus zu verfolgen

        System.out.println("--- Starte Tests für calculatePrice-Methode ---");

        // Testfall 1: Keine Rabatte, keine Extras, kein Sondermodell
        // Erwartet: Grundpreis 100.0
        allTestsPassed &= runSingleTest(calculator, "Test 1", 100, 0, 0, 0, 0, 100.0);

        // Testfall 2: Nur Händlerrabatt (10%) auf Grundpreis 100
        // Erwartet: 100 * (100-10)/100 = 90.0
        allTestsPassed &= runSingleTest(calculator, "Test 2", 100, 0, 0, 0, 10, 90.0);

        // Testfall 3: 3 Extras (10% Zubehörrabatt) auf Zubehörpreis 100
        // Erwartet: 100 (Grund) + 0 (Special) + 100 * (100-10)/100 (Extra) = 190.0
        allTestsPassed &= runSingleTest(calculator, "Test 3", 100, 0, 100, 3, 0, 190.0);

        // Testfall 4: 5 Extras (15% Zubehörrabatt) auf Zubehörpreis 100
        // Erwartet: 100 (Grund) + 0 (Special) + 100 * (100-15)/100 (Extra) = 185.0
        allTestsPassed &= runSingleTest(calculator, "Test 4", 100, 0, 100, 5, 0, 185.0);

        // Testfall 5: Händlerrabatt (10%) und 3 Extras (10% Zubehörrabatt)
        // Erwartet: (100 * 0.9) + 0 + (100 * 0.9) = 90 + 90 = 180.0
        allTestsPassed &= runSingleTest(calculator, "Test 5", 100, 0, 100, 3, 10, 180.0);

        // Testfall 6: Händlerrabatt (5%) und 5 Extras (15% Zubehörrabatt)
        // Erwartet: (100 * 0.95) + 0 + (100 * 0.85) = 95 + 85 = 180.0
        allTestsPassed &= runSingleTest(calculator, "Test 6", 100, 0, 100, 5, 5, 180.0);

        // Testfall 7: Alle Parameter aktiv
        // baseprice=200, specialprice=50, extraprice=100, extras=4, discount=5
        // Erwartet: (200 * 0.95) + 50 + (100 * 0.9) = 190 + 50 + 90 = 330.0
        allTestsPassed &= runSingleTest(calculator, "Test 7", 200, 50, 100, 4, 5, 330.0);

        // Testfall 8: Edge Case - 2 Extras (kein Rabatt)
        // Erwartet: 100 (Grund) + 0 (Special) + 100 (Extra) = 200.0
        allTestsPassed &= runSingleTest(calculator, "Test 8", 100, 0, 100, 2, 0, 200.0);

        System.out.println("\n--- Testergebnisse Zusammenfassung ---");
        if (allTestsPassed) {
            System.out.println("✅ Alle Tests erfolgreich!");
        } else {
            System.err.println("❌ Einige Tests sind fehlgeschlagen!");
        }
        return allTestsPassed;
    }

    /**
     * Hilfsmethode zum Ausführen und Protokollieren eines einzelnen Testfalls.
     */
    private static boolean runSingleTest(PriceCalculator calculator, String testName,
                                         double baseprice, double specialprice, double extraprice,
                                         int extras, double discount, double expectedResult) {
        double actualResult = calculator.calculatePrice(baseprice, specialprice, extraprice, extras, discount);
        // Verwende einen kleinen Toleranzbereich für double-Vergleiche
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
