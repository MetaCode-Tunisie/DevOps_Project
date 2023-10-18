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
// Given
        Long stockId = 1L;
        Long productId = 2L;

        Stock stock = new Stock();  // You should create a Stock object with appropriate data
        Product product = new Product();  // You should create a Product object with appropriate data

        when(stockRepository.findById(stockId)).thenReturn(java.util.Optional.ofNullable(stock));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // When
        Product result = productService.addProduct(new Product(), stockId);

        // Then
        verify(stockRepository, org.mockito.Mockito.times(1)).findById(eq(stockId));
        verify(productRepository, org.mockito.Mockito.times(1)).save(any(Product.class));

        // You can add more assertions based on your specific requirements and expected behavior
    }

    @Test
    void retrieveProduct() {
        // Given
        Long productId = 1L;
        Product expectedProduct = new Product();
        expectedProduct.setIdProduct(productId);

        // Mocking the behavior of the productRepository
        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(expectedProduct));

        // When
        Product result = productService.retrieveProduct(productId);


        // Then
        verify(productRepository, org.mockito.Mockito.times(1)).findById(eq(productId));


        // Assert
        assertNotNull(result);
        assertEquals(productId, result.getIdProduct());

    }

    @Test
    void retreiveAllProduct() {

        // Given
        Product product1 = new Product();
        product1.setIdProduct(1L);
        Product product2 = new Product();
        product2.setIdProduct(2L);

        List<Product> productList = Arrays.asList(product1, product2);

        // Mocking the behavior of the productRepository
        when(productRepository.findAll()).thenReturn(productList);

        // When
        List<Product> result = productService.retreiveAllProduct();

        // Then
        verify(productRepository, org.mockito.Mockito.times(1)).findAll();
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());



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

        // Given
        Long productId = 1L;

        // When
        productService.deleteProduct(productId);

        // Then
        verify(productRepository).deleteById(eq(productId));

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