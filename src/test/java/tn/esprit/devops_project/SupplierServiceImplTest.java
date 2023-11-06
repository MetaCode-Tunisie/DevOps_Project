package tn.esprit.devops_project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SupplierServiceImplTest {

    @Mock
    SupplierRepository supplierRepository;

    @InjectMocks
    SupplierServiceImpl supplierService;
    Supplier supplier = new Supplier(1L,"code","label1",SupplierCategory.ORDINAIRE);
    List<Supplier> supplierList = new ArrayList<Supplier>() {
        {
            add(new Supplier(2L,"code2","label2",SupplierCategory.ORDINAIRE));
            add(new Supplier(3L,"code3","label3",SupplierCategory.CONVENTIONNE));
        }
    };


    @Test
    void addSupplier() {
        // Create a new supplier object
        Supplier newSupplier = new Supplier(4L, "code4", "label4", SupplierCategory.ORDINAIRE);

        // Set up the mock behavior of the supplierRepository.save() method
        Mockito.when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(newSupplier);

        // Call the method under test
        Supplier savedSupplier = supplierService.addSupplier(newSupplier);

        // Verify the interactions and assertions
        Mockito.verify(supplierRepository, Mockito.times(1)).save(Mockito.any(Supplier.class));
        assertEquals(newSupplier, savedSupplier);
    }

    @Test
    void retrieveSupplier() {
        Mockito.when(supplierRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(supplier));
        Supplier supplier1 = supplierService.retrieveSupplier(2L);
        Assertions.assertNotNull(supplier1);
    }



}