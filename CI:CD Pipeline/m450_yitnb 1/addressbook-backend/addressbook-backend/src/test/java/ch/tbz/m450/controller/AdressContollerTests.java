package ch.tbz.m450.controller;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAddress_ShouldReturnCreatedAddress() {
        Address address = new Address();
        when(addressService.save(any(Address.class))).thenReturn(address);

        ResponseEntity<Address> response = addressController.createAddress(address);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(address, response.getBody());
        verify(addressService, times(1)).save(address);
    }

    @Test
    void getAddresses_ShouldReturnListOfAddresses() {
        List<Address> addresses = Arrays.asList(new Address(), new Address());
        when(addressService.getAll()).thenReturn(addresses);

        ResponseEntity<List<Address>> response = addressController.getAddresses();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(addresses, response.getBody());
        verify(addressService, times(1)).getAll();
    }

    @Test
    void getAddress_ShouldReturnAddressIfFound() {
        Address address = new Address();
        when(addressService.getAddress(1)).thenReturn(Optional.of(address));

        ResponseEntity<Address> response = addressController.getAddress(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(address, response.getBody());
        verify(addressService, times(1)).getAddress(1);
    }

    @Test
    void getAddress_ShouldReturn404IfNotFound() {
        when(addressService.getAddress(1)).thenReturn(Optional.empty());

        ResponseEntity<Address> response = addressController.getAddress(1);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(addressService, times(1)).getAddress(1);
    }
}
