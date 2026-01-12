package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressComparatorTest {

    @Test
    void compare_ShouldReturnZeroForSameAttributes() {
        Address address1 = new Address(1, "John", "Doe", "123456789", null);
        Address address2 = new Address(2, "John", "Doe", "123456789", null);

        AddressComparator comparator = new AddressComparator();

        int result = comparator.compare(address1, address2);

        assertEquals(0, result);
    }

    @Test
    void compare_ShouldReturnNegativeWhenFirstnameIsAlphabeticallyBefore() {
        Address address1 = new Address(1, "Alice", "Doe", "123456789", null);
        Address address2 = new Address(2, "Bob", "Smith", "987654321", null);

        AddressComparator comparator = new AddressComparator();

        int result = comparator.compare(address1, address2);

        assertEquals(-1, result);
    }

    @Test
    void compare_ShouldReturnPositiveWhenFirstnameIsAlphabeticallyAfter() {
        Address address1 = new Address(1, "Charlie", "Doe", "123456789", null);
        Address address2 = new Address(2, "Bob", "Smith", "987654321", null);

        AddressComparator comparator = new AddressComparator();

        int result = comparator.compare(address1, address2);

        assertEquals(1, result);
    }

    @Test
    void compare_ShouldCompareByLastnameWhenFirstnamesAreEqual() {
        Address address1 = new Address(1, "John", "Brown", "123456789", null);
        Address address2 = new Address(2, "John", "Smith", "987654321", null);

        AddressComparator comparator = new AddressComparator();

        int result = comparator.compare(address1, address2);

        assertEquals(-17, result);
    }

    @Test
    void compare_ShouldCompareByPhonenumberWhenFirstAndLastnameAreEqual() {
        Address address1 = new Address(1, "John", "Doe", "123456789", null);
        Address address2 = new Address(2, "John", "Doe", "987654321", null);

        AddressComparator comparator = new AddressComparator();

        int result = comparator.compare(address1, address2);

        assertEquals(-8, result);
    }

    @Test
    void compare_ShouldHandleNullValuesInFirstname() {
        Address address1 = new Address(1, null, "Doe", "123456789", null);
        Address address2 = new Address(2, "Alice", "Doe", "987654321", null);

        AddressComparator comparator = new AddressComparator();

        int result = comparator.compare(address1, address2);

        assertEquals(-1, result);
    }

    @Test
    void compare_ShouldHandleNullValuesInLastname() {
        Address address1 = new Address(1, "John", null, "123456789", null);
        Address address2 = new Address(2, "John", "Smith", "987654321", null);

        AddressComparator comparator = new AddressComparator();

        int result = comparator.compare(address1, address2);

        assertEquals(-1, result);
    }

    @Test
    void compare_ShouldHandleNullValuesInPhonenumber() {
        Address address1 = new Address(1, "John", "Doe", null, null);
        Address address2 = new Address(2, "John", "Doe", "987654321", null);

        AddressComparator comparator = new AddressComparator();

        int result = comparator.compare(address1, address2);

        assertEquals(-1, result);
    }
}
