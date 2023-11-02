package tn.esprit.devops_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SupplierServiceImplTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllSuppliers() {
        List<Supplier> expectedSuppliers = new ArrayList<>();

        when(supplierRepository.findAll()).thenReturn(expectedSuppliers);
        List<Supplier> actualSuppliers = supplierService.retrieveAllSuppliers();

        assertEquals(expectedSuppliers, actualSuppliers);
        verify(supplierRepository, times(1)).findAll();
    }

    @Test
    void addSupplier() {
        Supplier supplier = new Supplier();

        when(supplierRepository.save(supplier)).thenReturn(supplier);

        Supplier addedSupplier = supplierService.addSupplier(supplier);

        assertEquals(supplier, addedSupplier);
        verify(supplierRepository, times(1)).save(supplier);
    }

    @Test
    void updateSupplier() {
        Supplier supplier = new Supplier();

        when(supplierRepository.save(supplier)).thenReturn(supplier);

        Supplier updatedSupplier = supplierService.updateSupplier(supplier);

        assertEquals(supplier, updatedSupplier);
        verify(supplierRepository, times(1)).save(supplier);
    }

    @Test
    void deleteSupplier() {
        Long supplierId = 1L;
        supplierService.deleteSupplier(supplierId);
        verify(supplierRepository, times(1)).deleteById(supplierId);
    }

    @Test
    void retrieveSupplier() {
        Long supplierId = 1L;
        Supplier expectedSupplier = new Supplier();
        when(supplierRepository.findById(supplierId)).thenReturn(java.util.Optional.of(expectedSupplier));

        Supplier actualSupplier = supplierService.retrieveSupplier(supplierId);

        assertEquals(expectedSupplier, actualSupplier);
        verify(supplierRepository, times(1)).findById(supplierId);
    }
}