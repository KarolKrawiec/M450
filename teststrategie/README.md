 **Übungen 1–3** komplett für dich.

# Übung 1 – Rabattlogik: abstrakte vs. konkrete Testfälle

## A) Abstrakte Testfälle (mit Operatoren)

| ID | Eingabe (Kaufpreis)       | Erwarteter Rabatt  |
| -- | ------------------------- | ------------------ |
| A1 | `< 15’000`                | `0%`               |
| A2 | `>= 15’000 AND <= 20’000` | `5%`               |
| A3 | `> 20’000 AND < 25’000`   | `7%`               |
| A4 | `>= 25’000`               | `8.5%`             |
| A5 | `<= 0` (inkorrekt)        | Validierungsfehler |

> Kantenfälle klar definiert: **15’000 → 5%**, **20’000 → 5%**, **25’000 → 8.5%**.

## B) Konkrete Testfälle (mit Zahlenwerten)

| ID  |  Kaufpreis (CHF) | Erwarteter Rabatt               |
| --- | ---------------: | ------------------------------- |
| K1  |        14’999.95 | 0%                              |
| K2  |        15’000.00 | 5%                              |
| K3  |        20’000.00 | 5%                              |
| K4  |        20’000.01 | 7%                              |
| K5  |        24’999.99 | 7%                              |
| K6  |        25’000.00 | 8.5%                            |
| K7  |        50’000.00 | 8.5%                            |
| K8  |             0.00 | Validierungsfehler              |
| K9  |            −1.00 | Validierungsfehler              |
| K10 | "zwanzigtausend" | Validierungsfehler (Typprüfung) |

---

# Übung 2 – 5 funktionale Black-Box-Tests (Autovermietung)


| ID | Beschreibung                                                                                | Erwartetes Resultat                                             | Effektives Resultat | Status | Mögliche Ursache |
| -- | ------------------------------------------------------------------------------------------- | --------------------------------------------------------------- | ------------------- | ------ | ---------------- |
| F1 | **Suche & Verfügbarkeit:** Standort „Zürich HB“, Datum/Zeit wählen, Fahrer > 18             | Liste verfügbarer Fahrzeuge mit korrekten Tarifen & Filtern     | –                   | –      | –                |
| F2 | **Preisbildung:** Auswahl Fahrzeug + Zusatzoptionen (z. B. Zusatzfahrer), Steuern/Gebühren  | Gesamtpreis aktualisiert sich korrekt, keine versteckten Kosten | –                   | –      | –                |
| F3 | **Kundenweg Gast/Login:** Buchung als Gast **oder** Login möglich                           | Beide Wege funktionieren, Pflichtfelder werden validiert        | –                   | –      | –                |
| F4 | **Bezahlung:** Gültige Karte → Bestätigung; ungültige Karte → verständliche Fehlermeldung   | Erfolgsseite mit Buchungsnummer **oder** klarer Fehler          | –                   | –      | –                |
| F5 | **Bestätigung & Verwaltung:** E-Mail mit Details; „Manage booking“ ändert/storniert korrekt | Bestätigungsmail empfangen; Änderungen wirken im System         | –                   | –      | –                |


---

# Teststrategie – Simple Bank-Software (Aufgabe 3)

## 1. Kurzbeschreibung der Software

Die Konsole-App simuliert einen Bankschalter:

- In `Main` werden fünf Beispielkonten erstellt (Rockefeller, Gates, usw.).
- Über die Klasse `Counter` kann der Benutzer per Menü:
  - ein Konto auswählen,
  - den Kontostand anzeigen,
  - Geld einzahlen,
  - Geld abheben,
  - Geld auf ein anderes Konto überweisen,
  - neue Konten erstellen,
  - den Wechselkurs über `ExchangeRateOkhttp` abfragen.
- Die Klasse `Bank` verwaltet alle `Account`-Objekte (erstellen, holen, löschen, anzeigen).
- Ein `Account` speichert: Kundennachname, Währung (`Currency`), Kontostand (`balance`) und eine ID.

---

## 2. Mögliche Black-Box-Testfälle (Benutzersicht)

Diese Testfälle gehen davon aus, dass ich nur das Verhalten über die Konsole kenne (kein Blick in den Code).

| ID   | Beschreibung                               | Eingaben / Vorbedingung                                                | Erwartetes Ergebnis                                                                 |
|------|--------------------------------------------|-------------------------------------------------------------------------|-------------------------------------------------------------------------------------|
| BB1  | Programmstart                              | Programm auf der Konsole starten                                       | Starttext / Begrüssung wird angezeigt, Menü vom `Counter` erscheint                |
| BB2  | Konto-Liste anzeigen                       | Im Menü Option „alle Konten anzeigen“ wählen                            | Liste aller Konten mit Nummer, Namen und Währung wird angezeigt                    |
| BB3  | Kontostand anzeigen                        | Konto-Nr. eines existierenden Kontos eingeben, Option „Kontostand“     | Aktueller Kontostand mit 2 Nachkommastellen und richtiger Währung wird angezeigt   |
| BB4  | Geld einzahlen                             | Konto auswählen, Option „Einzahlen“, z. B. Betrag 100.00 eingeben       | Bestätigung und neuer Kontostand = alter Kontostand + 100.00                       |
| BB5  | Geld abheben (genug Guthaben)              | Konto mit mind. 100 CHF, Option „Abheben“, Betrag 50.00 eingeben       | Bestätigung und neuer Kontostand = alter Kontostand – 50.00                        |
| BB6  | Geld abheben (zu wenig Guthaben)           | Konto mit z. B. 20 CHF, Option „Abheben“, Betrag 100.00 eingeben       | Fehlermeldung (nicht genug Guthaben), Kontostand bleibt unverändert                |
| BB7  | Geld auf anderes Konto überweisen          | Konto A wählen, Option „Überweisen“, Konto B auswählen, Betrag 10.00    | Kontostand von A wird um 10.00 reduziert, Kontostand von B um 10.00 erhöht         |
| BB8  | Überweisung mit ungültiger Zielkontonummer | Konto A wählen, Option „Überweisen“, nicht existierende Konto-Nr.       | Fehlermeldung „Konto nicht gefunden“ o.ä., Kontostände bleiben unverändert         |
| BB9  | Neues Konto erstellen                      | Option „Konto erstellen“, Name und Währung eingeben, Startsaldo > 0     | Neues Konto mit neuer ID wird angelegt und in der Kontoliste angezeigt             |
| BB10 | Wechselkurs abfragen                       | Option „Wechselkurs abfragen“ wählen, z. B. von EUR zu CHF              | Wechselkurs wird abgefragt und angezeigt oder sinnvoller Fehler bei API-Problem    |
| BB11 | Programm beenden                           | Im Hauptmenü „q“ eingeben                                              | Programm beendet sich geordnet (kein Fehler-Stacktrace)                            |

---

## 3. Kandidaten für White-Box-Testfälle (Methoden im Code)

Hier geht es um Methoden, die man mit Unit-Tests (z. B. JUnit) gezielt testen könnte.

### 3.1 Wichtige Methoden in `Account`

```java
public Account(String userLastName, Currency currency, double startBalance)
public void deposit(double amount)
public boolean withdraw(double amount)
public double getBalance()
public Currency getCurrency()
public String getUserLastName()
public int getId()
public void pseudoDeleteAccount()


