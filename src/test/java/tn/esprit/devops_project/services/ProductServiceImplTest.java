package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class ProductServiceImplTest {

    List<Product> productList = new ArrayList<Product>(){{
        add( new Product(1L,"Maryem",20,22));
        add( new Product(2L,"hazem",30,10));

    }};


    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    @InjectMocks
    private StockServiceImpl stockService;
    @Mock
    StockRepository stockRepository;




    @Test
    void addProduct() {
        long idStock = 1L;
        // Create a mock product
        Product product = new Product().builder().title("Salem").price(122).quantity(12).build();

        // Create a mock stock
        Stock stock = new Stock();
        stock.setIdStock(idStock);

        // Stub the behavior of the mocked repository when saving a product
        when(stockRepository.findById(idStock)).thenReturn(Optional.of(stock));
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.addProduct(product, idStock);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Salem");

        // Verify that the repository's save method was called
        verify(productRepository).save(product);


    }

    @Test
    void retreiveAllProduct() {


        when(productService.retreiveAllProduct()).thenReturn(productList);
        List<Product> list = productService.retreiveAllProduct();
        assertThat(list).isNotNull();
        assertThat(list).isNotEmpty();
        assertThat(list).isEqualTo(productList);



    }

    @Test
    void retrieveProductByCategory() {

    }

    @Test
    void deleteProduct() {

        Long productId = 1L; // Replace with an actual ID

        // Create a Product object for testing
        Product product = new Product(/* Initialize with required data */);

        // Define the behavior of the mock repository
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Call the service method to test
        assertDoesNotThrow(() -> productService.deleteProduct(productId));

        // Verify that the delete method of the repository is called with the correct ID
        verify(productRepository).deleteById(productId);

    }

    @Test
    void retreiveProductStock() {



    }
    private void someMethodThatUsesRetrieveProductStock(Long stockId) {
        // You can call other methods in your service or other services
        // that indirectly use retrieveProductStock
        stockService.retrieveProductStock(stockId);
        // Perform additional assertions or verifications as needed
    }

}