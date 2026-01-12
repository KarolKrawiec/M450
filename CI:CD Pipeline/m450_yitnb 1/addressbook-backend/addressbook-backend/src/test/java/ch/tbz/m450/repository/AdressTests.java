package ch.tbz.m450.repository;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void testAllArgsConstructor() {
        Date date = new Date();
        Address address = new Address(1, "John", "Doe", "123456789", date);

        assertEquals(1, address.getId());
        assertEquals("John", address.getFirstname());
        assertEquals("Doe", address.getLastname());
        assertEquals("123456789", address.getPhonenumber());
        assertEquals(date, address.getRegistrationDate());
    }

    @Test
    void testNoArgsConstructor() {
        Address address = new Address();

        address.setId(1);
        address.setFirstname("John");
        address.setLastname("Doe");
        address.setPhonenumber("123456789");
        Date date = new Date();
        address.setRegistrationDate(date);

        assertEquals(1, address.getId());
        assertEquals("John", address.getFirstname());
        assertEquals("Doe", address.getLastname());
        assertEquals("123456789", address.getPhonenumber());
        assertEquals(date, address.getRegistrationDate());
    }

    @Test
    void testSettersAndGetters() {
        Address address = new Address();
        Date date = new Date();

        address.setId(1);
        address.setFirstname("Jane");
        address.setLastname("Smith");
        address.setPhonenumber("987654321");
        address.setRegistrationDate(date);

        assertEquals(1, address.getId());
        assertEquals("Jane", address.getFirstname());
        assertEquals("Smith", address.getLastname());
        assertEquals("987654321", address.getPhonenumber());
        assertEquals(date, address.getRegistrationDate());
    }

    @Test
    void testEquality() {
        Date date = new Date();
        Address address1 = new Address(1, "Alice", "Brown", "111222333", date);
        Address address2 = new Address(1, "Alice", "Brown", "111222333", date);

        assertNotEquals(address1, address2);
    }

    @Test
    void testInequality() {
        Date date = new Date();
        Address address1 = new Address(1, "Alice", "Brown", "111222333", date);
        Address address2 = new Address(2, "Bob", "Green", "444555666", date);

        assertNotEquals(address1, address2);
    }
}
