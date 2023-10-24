package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.client.ExpectedCount;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@SpringBootTest
class ProductServiceImplTest {
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
        Product product = new Product(/* Initialize with required data */);
        Long stockId = 1L; // Replace with an actual ID

        // Create a Stock object for testing
        Stock stock = new Stock(/* Initialize with required data */);

        // Define the behavior of the mock repositories
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));
        when(productRepository.save(product)).thenReturn(product);

        // Call the service method to test
        Product result = productService.addProduct(product, stockId);

        // Add your assertions here to verify the result


    }

    @Test
    void retrieveProduct() {
        Long productId = 1L; // Replace with an actual ID
        // Create a Product object for testing
        Product product = new Product(/* Initialize with required data */);

        // Define the behavior of the mock repository
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Call the service method to test
        Product result = productService.retrieveProduct(productId);


    }

    @Test
    void retreiveAllProduct() {

        // Create a list of Products for testing
        List<Product> products = new ArrayList<>();
        products.add(new Product(/* Initialize with required data */));
        products.add(new Product(/* Initialize with required data */));

        // Define the behavior of the mock repository
        when(productRepository.findAll()).thenReturn(products);

        // Call the service method to test
        List<Product> result = productService.retreiveAllProduct();

        // Assertions
        assertEquals(products.size(), result.size(), "Size of the product list should match.");



    }

    @Test
    void retrieveProductByCategory() {

        Product product1 = new Product();
        product1.setIdProduct(1L);
        product1.setCategory(ProductCategory.ELECTRONICS);

        Product product2 = new Product();
        product2.setIdProduct(2L);
        product2.setCategory(ProductCategory.ELECTRONICS);

        List<Product> productList = Arrays.asList(product1, product2);

        // Mocking the behavior of the productRepository
        when(productRepository.findByCategory(ProductCategory.ELECTRONICS)).thenReturn(productList);

        // When
        List<Product> result = productService.retrieveProductByCategory(ProductCategory.ELECTRONICS);

        // Then
        verify(productRepository).findByCategory(eq(ProductCategory.ELECTRONICS));
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

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
        // Given
        Long stockId = 1L;
        Stock stock = new Stock();
        stock.setIdStock(stockId);
        stock.setTitle("Stock 1");

        // Mocking the behavior of the stockRepository
        when(stockRepository.findById(stockId)).thenReturn(java.util.Optional.ofNullable(stock));

        // When
        // Call a method that indirectly triggers retrieveProductStock
        someMethodThatUsesRetrieveProductStock(stockId);

        // Then
        verify(stockRepository).findById(eq(stockId));


    }
    private void someMethodThatUsesRetrieveProductStock(Long stockId) {
        // You can call other methods in your service or other services
        // that indirectly use retrieveProductStock
        stockService.retrieveProductStock(stockId);
        // Perform additional assertions or verifications as needed
    }
}