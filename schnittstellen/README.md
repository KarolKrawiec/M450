# Lösung: Addressbook Backend Tests & Comparator

Dieses Dokument beschreibt die Schritte zur Lösung der Aufgaben 1 und 2. Es umfasst das Reparieren der Sortierlogik, das Erstellen von Unit-Tests mit JUnit 5 & Mockito sowie die Erweiterung der Vergleichslogik.

## Projektstruktur für Tests

Die Test-Dateien müssen im Ordner `src/test/java` exakt dieselbe Paketstruktur haben wie die Klassen im `src/main/java`.

```text
src/test/java/ch/tbz/m450/
├── controller/
│   └── AddressControllerTest.java
├── repository/
│   └── AddressTest.java
├── service/
│   └── AddressServiceTest.java
└── util/
    └── AddressComparatorTest.java
```

---

## Aufgabe 1: Implementierung & Basis-Tests

### 1. Comparator korrigieren (Basis)
Die Methode gab ursprünglich nur `-1` zurück. Wir ändern dies, sodass nach **Nachnamen** sortiert wird.

**Datei:** `src/main/java/ch/tbz/m450/util/AddressComparator.java`

```java
package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {

    @Override
    public int compare(Address a1, Address a2) {
        // Basis-Implementierung für Aufgabe 1: Nur Nachname
        if (a1.getLastname() == null || a2.getLastname() == null) {
            return 0;
        }
        return a1.getLastname().compareToIgnoreCase(a2.getLastname());
    }
}
```

### 2. Entity Test (Model)
Testet, ob das Objekt korrekt erstellt wird und Getter/Setter funktionieren.

**Datei:** `src/test/java/ch/tbz/m450/repository/AddressTest.java`

```java
package ch.tbz.m450.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address(1, "Test", "User", "1234", new Date());
    }

    @Test
    void testAddressCreation() {
        assertNotNull(address);
        assertEquals(1, address.getId());
        assertEquals("Test", address.getFirstname());
    }

    @Test
    void testSetters() {
        address.setLastname("Changed");
        assertEquals("Changed", address.getLastname());
    }
}
```

### 3. Service Test (mit Mockito)
Hier wird die Datenbank (Repository) "weg gemockt", um den Service isoliert zu testen.

**Datei:** `src/test/java/ch/tbz/m450/service/AddressServiceTest.java`

```java
package ch.tbz.m450.service;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address a1;
    private Address a2;

    @BeforeEach
    void setUp() {
        a1 = new Address(1, "Albert", "Zuse", "111", new Date());
        a2 = new Address(2, "Berta", "Muster", "222", new Date());
    }

    @Test
    void testGetAllSorted() {
        // Mocking: Repository gibt Liste zurück
        when(addressRepository.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<Address> result = addressService.getAll();

        // Prüfung: Comparator sollte "Muster" vor "Zuse" sortieren
        assertEquals("Muster", result.get(0).getLastname());
        assertEquals("Zuse", result.get(1).getLastname());
    }

    @Test
    void testSave() {
        when(addressRepository.save(any(Address.class))).thenReturn(a1);
        Address saved = addressService.save(a1);
        assertEquals("Zuse", saved.getLastname());
    }
}
```

### 4. Controller Test
Testet die HTTP-Endpunkte.

**Datei:** `src/test/java/ch/tbz/m450/controller/AddressControllerTest.java`

```java
package ch.tbz.m450.controller;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Test
    void testCreateAddress() throws Exception {
        Address address = new Address(1, "Hans", "Muster", "123", new Date());
        when(addressService.save(any(Address.class))).thenReturn(address);

        mockMvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(address)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAddresses() throws Exception {
        when(addressService.getAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/address"))
                .andExpect(status().isOk());
    }
}
```

---

## Aufgabe 2: Erweiterung (Vorname)

### 1. Test für die Erweiterung (TDD)
Wir testen den Fall: Gleicher Nachname, aber unterschiedliche Vornamen.

**Datei:** `src/test/java/ch/tbz/m450/util/AddressComparatorTest.java`

```java
package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddressComparatorTest {

    private AddressComparator comparator;
    private Address a1;
    private Address a2;

    @BeforeEach
    void setUp() {
        comparator = new AddressComparator();
        // Gleicher Nachname "Muster"
        a1 = new Address(1, "Anna", "Muster", "1", new Date());
        a2 = new Address(2, "Bernd", "Muster", "2", new Date());
    }

    @Test
    void testCompareSameLastnameDifferentFirstname() {
        // Anna kommt vor Bernd -> Ergebnis muss negativ sein (< 0)
        assertTrue(comparator.compare(a1, a2) < 0, "Anna sollte vor Bernd kommen");
    }
}
```

### 2. Comparator erweitern
Wir passen die Logik an, um den obigen Test zu bestehen.

**Datei:** `src/main/java/ch/tbz/m450/util/AddressComparator.java` (Update)

```java
package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {

    @Override
    public int compare(Address a1, Address a2) {
        // 1. Vergleich nach Nachname
        int lastnameCompare = a1.getLastname().compareToIgnoreCase(a2.getLastname());
        
        // 2. Wenn Nachnamen NICHT gleich sind, gib das Ergebnis zurück
        if (lastnameCompare != 0) {
            return lastnameCompare;
        }
        
        // 3. Erweiterung für Aufgabe 2:
        // Wenn Nachnamen gleich sind, vergleiche Vorname
        return a1.getFirstname().compareToIgnoreCase(a2.getFirstname());
    }
}
```

---

## Durchführung der Tests

Um sicherzustellen, dass alles funktioniert:

1.  Öffne das Terminal oder die IDE.
2.  Führe `mvn test` aus (oder Rechtsklick auf den `test`-Ordner -> "Run Tests").
3.  Alle Tests sollten **grün** sein.
