package ch.tbz.m450.service;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedAddress() {
        Address address = new Address(1, "John", "Doe", "123456789", null);
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address savedAddress = addressService.save(address);

        assertEquals(address, savedAddress);
    }

    @Test
    void getAll_ShouldReturnSortedAddresses() {
        Address address1 = new Address(1, "Alice", "Smith", "123456789", null);
        Address address2 = new Address(2, "Bob", "Johnson", "987654321", null);
        Address address3 = new Address(3, "Charlie", "Brown", "555666777", null);
        List<Address> unsortedAddresses = Arrays.asList(address2, address3, address1);
        when(addressRepository.findAll()).thenReturn(unsortedAddresses);

        List<Address> sortedAddresses = addressService.getAll();

        assertEquals(Arrays.asList(address1, address2, address3), sortedAddresses);
    }

    @Test
    void getAddress_ShouldReturnAddressIfFound() {
        Address address = new Address(1, "John", "Doe", "123456789", null);
        when(addressRepository.findById(1)).thenReturn(Optional.of(address));

        Optional<Address> foundAddress = addressService.getAddress(1);

        assertTrue(foundAddress.isPresent());
        assertEquals(address, foundAddress.get());
    }

    @Test
    void getAddress_ShouldReturnEmptyIfNotFound() {
        when(addressRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Address> foundAddress = addressService.getAddress(1);

        assertFalse(foundAddress.isPresent());
    }
}
