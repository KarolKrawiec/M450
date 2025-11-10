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

> Optional weiter testen: Altersregeln/Fees, One-Way-Mieten, Extras (Winterreifen), Stornobedingungen.

---

# Übung 3 – Simple Bank-Software (TBZ m450)


## A) Mögliche Black-Box-Testfälle (aus Benutzersicht)

| ID  | Feature               | Eingabe                                          | Erwartetes Resultat                                          |
| --- | --------------------- | ------------------------------------------------ | ------------------------------------------------------------ |
| B1  | Konto anlegen         | gültiger Name, Anfangssaldo ≥ 0                  | Konto-ID wird erstellt, Saldo korrekt initialisiert          |
| B2  | Kontostand abfragen   | bestehende Konto-ID                              | Rückgabe `balance` als **BigDecimal** mit 2 Nachkommastellen |
| B3  | Einzahlung            | Konto-ID, Betrag > 0                             | Neuer Saldo = alter + Betrag                                 |
| B4  | Auszahlung            | Konto-ID, Betrag > 0, Saldo ≥ Betrag             | Neuer Saldo = alter − Betrag                                 |
| B5  | Auszahlung unzulässig | Konto-ID, Betrag > Saldo                         | Fehler „unz. Deckung“ (keine Änderung am Saldo)              |
| B6  | Überweisung           | Quell-ID, Ziel-ID, Betrag > 0, Deckung vorhanden | Beide Salden korrekt angepasst; Transaktion atomar           |
| B7  | Überweisung Fehler    | Quell-ID existiert nicht                         | Fehler „Konto nicht gefunden“                                |
| B8  | Input-Validierung     | Betrag ≤ 0 oder ungültiges Format                | Valider Fehlerstatus, keine Zustandsänderung                 |
| B9  | API-Fehlerbehandlung  | Netzwerk down / 500er vom Server                 | Robuste Fehlermeldung, kein Crash                            |
| B10 | JSON-Parsing          | Server liefert unerwartetes Feld/fehlendes Feld  | Sinnvolle Fehlermeldung, Fallback/Abbruch                    |

## B) White-Box-Kandidaten (Methoden/Logik, die man gezielt unit-testen sollte)

> Bezeichner variieren je nach Code – die Funktionalität sollte so oder ähnlich existieren:

* `AccountService.createAccount(name, initialBalance)`
* `AccountService.getBalance(accountId)`
* `AccountService.deposit(accountId, amount)`
* `AccountService.withdraw(accountId, amount)`
* `TransferService.transfer(fromId, toId, amount)` **(Transaktionsatomarität & Rollback testen)**
* `AmountValidator.validate(amount)` (**Grenzwerte, Rundung**)
* `BankApiClient` (OkHttp-Requests: Zeitüberschreitungen, Fehlercodes, Retries)
* `JsonMapper.parseAccount(json)` / `parseError(json)` (Gson-Mapping, Null-Handling)

## C) Verbesserungsvorschläge / Best Practices (kurz & knackig)

* **Geldbeträge als `BigDecimal`** (Skalierung/`RoundingMode.HALF_EVEN`), keine `double`-Mathematik.
* **Input-Validierung überall** (negativ, Null, zu viele Nachkommastellen).
* **Fehlerbehandlung**: Unterschiedliche Exceptions/Result-Typen für 4xx/5xx/Network.
* **Transaktionen**: Überweisung atomar (im Speicher mit Locks oder DB-Transaktion).
* **Thread-Safety**: Synchronisation/Locks bei gemeinsamem Zustand; ggf. immutable DTOs.
* **Schichten**: Trennung Domain (Service) ↔︎ Adapter (OkHttp/Gson) ↔︎ UI/CLI.
* **Tests**: JUnit 5; Mocks/Stubs für HTTP (z. B. MockWebServer); **Property-based Tests** für Beträge.
* **Logging** (strukturiert), **Konfiguration** (Timeouts, Base-URL) via Env/Config.
* **Namensgebung & Clean Code** (kleine Methoden, Single Responsibility).

## D) Tabellarische Testübersicht (für dein Repo, Markdown-ready)

```markdown
# Testfälle – Simple Bank App

| ID  | Typ         | Beschreibung                              | Eingabe/Arrange                          | Erwartet                              | Status | Notizen |
|-----|-------------|--------------------------------------------|------------------------------------------|----------------------------------------|--------|--------|
| B1  | Black-Box   | Konto anlegen                              | name="G", init=100.00                    | 201 Created, id!=null, balance=100.00  |        |        |
| B2  | Black-Box   | Kontostand                                 | id=EXISTING                              | 200 OK, balance=… (BigDecimal, 2 dp)   |        |        |
| B3  | Black-Box   | Einzahlung                                 | id=EXISTING, amount=25.50                | balance+=25.50                         |        |        |
| B4  | Black-Box   | Auszahlung                                 | id=EXISTING, amount=10.00, cover ok      | balance-=10.00                         |        |        |
| B5  | Black-Box   | Auszahlung (keine Deckung)                 | id=EXISTING, amount=9999                 | 400/422 Error, balance unverändert     |        |        |
| B6  | Black-Box   | Überweisung atomar                         | from=A, to=B, amount=5.00                | A-=5.00, B+=5.00 (beides oder keins)   |        |        |
| W1  | White-Box   | AmountValidator – Grenzwerte               | 0, -1, 0.001, 1.005                       | invalid, invalid, invalid, rounded OK  |        |        |
| W2  | White-Box   | TransferService – Rollback bei Fehler      | from=A (5.00), to=NE |                    | keine Änderung bei A                   |        |        |
| W3  | White-Box   | BankApiClient – Timeout/500 Handling       | simulate timeout / HTTP 500              | Retry/Fehlerobjekt, kein Crash         |        |        |
```

---

[1]: https://www.europcar.ch/en-ch?utm_source=chatgpt.com "Europcar Switzerland: Car Rental - Car & Utility Van Hire"
[2]: https://gitlab.com/ch-tbz-it/Stud/m450/m450?utm_source=chatgpt.com "m450 - TBZ-IT-Informatik"
[3]: https://github.com/square/okhttp?utm_source=chatgpt.com "square/okhttp: Square's meticulous HTTP client for the JVM ..."

