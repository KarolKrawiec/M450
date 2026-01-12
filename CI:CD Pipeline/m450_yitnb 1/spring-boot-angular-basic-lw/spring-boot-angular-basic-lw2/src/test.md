### API Testskripte / Übung 1

#### 1. **GET-Anfrage: Alle Studenten abrufen**

**Endpoint:** `GET /students`

**Testskripte:**
```javascript
// Test auf erfolgreichen Statuscode (200)
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

// Test, ob die Antwort ein Array ist
pm.test("Response is an array", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.be.an('array');
});

// Test, ob jeder Student im Array das Feld 'name' enthält
pm.test("Each student has a 'name'", function () {
    var jsonData = pm.response.json();
    jsonData.forEach(function(student) {
        pm.expect(student).to.have.property('name');
    });
});
```

---

#### 2. **POST-Anfrage: Einen neuen Studenten hinzufügen**

**Endpoint:** `POST /students`

**Beispiel für den Body:**
```json
{
    "name": "Jane Doe",
    "age": 21,
    "course": "Software Engineering"
}
```

**Testskripte:**
```javascript
// Test für successful response (status code 200)
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

// Test ob der response body nicht leer ist (Da der Endpunkt keine Daten zurück schickt)
pm.test("Response body is empty", function () {
    pm.expect(pm.response.text()).to.eql('');
});

```

---
