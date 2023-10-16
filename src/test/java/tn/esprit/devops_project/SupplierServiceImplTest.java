package tn.esprit.devops_project;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SupplierServiceImplTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        supplierService = new SupplierServiceImpl(supplierRepository);
    }

    @Test
    void retrieveAllSuppliers() {
        List<Supplier> expectedSuppliers = new ArrayList<>();
        expectedSuppliers.add(new Supplier().builder().idSupplier(1L).build());
        expectedSuppliers.add(new Supplier().builder().idSupplier(2L).build());
        when(supplierRepository.findAll()).thenReturn(expectedSuppliers);

        List<Supplier> actualSuppliers = supplierService.retrieveAllSuppliers();

        assertEquals(expectedSuppliers, actualSuppliers);
    }

    @Test
    void addSupplier() {
        Supplier expectedSupplier = new Supplier().builder().idSupplier(1L).code("Code").label("Label").supplierCategory(SupplierCategory.ORDINAIRE).build();
        when(supplierRepository.save(expectedSupplier)).thenReturn(expectedSupplier);

        Supplier actualSupplier = supplierService.addSupplier(expectedSupplier);

        assertEquals(expectedSupplier, actualSupplier);
    }

    @Test
    void updateSupplier() {
        Supplier expectedSupplier = new Supplier().builder().idSupplier(1L).code("Update Code").label("Update Label").supplierCategory(SupplierCategory.CONVENTIONNE).build();
        when(supplierRepository.save(expectedSupplier)).thenReturn(expectedSupplier);

        Supplier actualSupplier = supplierService.updateSupplier(expectedSupplier);

        assertEquals(expectedSupplier, actualSupplier);
    }

    @Test
    void deleteSupplierById() {
        Long supplierId = 1L;

        supplierService.deleteSupplier(supplierId);

        verify(supplierRepository, times(1)).deleteById(supplierId);
    }

    @Test
    void retrieveSupplier_ExistingSupplierId() {
        Long supplierId = 1L;
        Supplier expectedSupplier = new Supplier();
        expectedSupplier.setIdSupplier(supplierId);
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(expectedSupplier));

        Supplier actualSupplier = supplierService.retrieveSupplier(supplierId);

        assertEquals(expectedSupplier, actualSupplier);
    }

    @Test
    void retrieveSupplier_NonExistingSupplierId() {
        Long supplierId = 1L;
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            supplierService.retrieveSupplier(supplierId);
        });

        assertEquals("Invalid user Id:" + supplierId, exception.getMessage());
    }
}