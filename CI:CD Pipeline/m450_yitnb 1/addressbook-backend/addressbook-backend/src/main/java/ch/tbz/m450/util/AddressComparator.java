package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;

import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {

    @Override
    public int compare(Address a1, Address a2) {
        int firstnameComparison = compareNullableStrings(a1.getFirstname(), a2.getFirstname());
        if (firstnameComparison != 0) {
            return firstnameComparison;
        }

        int lastnameComparison = compareNullableStrings(a1.getLastname(), a2.getLastname());
        if (lastnameComparison != 0) {
            return lastnameComparison;
        }

        return compareNullableStrings(a1.getPhonenumber(), a2.getPhonenumber());
    }

    private int compareNullableStrings(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return 0;
        }
        if (s1 == null) {
            return -1;
        }
        if (s2 == null) {
            return 1;
        }
        return s1.compareToIgnoreCase(s2);
    }
}
