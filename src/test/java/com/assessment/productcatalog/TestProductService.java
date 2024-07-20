package com.assessment.productcatalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.assessment.productcatalog.constant.ProductConstants;
import com.assessment.productcatalog.document.Product;
import com.assessment.productcatalog.exception.ProductNotFoundException;
import com.assessment.productcatalog.repository.ProductRepository;
import com.assessment.productcatalog.service.ProductService;

@SpringBootTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=4.0.2")
public class TestProductService {

	@MockBean
    private ProductRepository productRepository;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ProductService productService;

	Product product = null;
	
    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId("PROD001");
        product.setName("Neck Tshirt");
        product.setDescription("Men Long Sleves Tshirt");
        product.setPrice(499D);
        product.setCategory("TShirt");
    }
    
    @Test
    void testGetAllProducts() {
        List<Product> products = Arrays.asList(product);
        when(productRepository.findAll()).thenReturn(products);
        List<Product> result = productService.getAllProducts();
        assertEquals(1, result.size());
        assertEquals("PROD001", result.get(0).getId());
        verify(productRepository, times(1)).findAll();
    }
    
    @Test
    void testGetProductById() {
        when(productRepository.findById("PROD001")).thenReturn(Optional.of(product));
        Product result = productService.getProductById("PROD001");
        assertNotNull(result);
        assertEquals("TShirt", result.getCategory());
        verify(productRepository, times(1)).findById("PROD001");
    }
    
    @Test
    void testGetProductByIdNotFound() {
        when(productRepository.findById("PROD002")).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById("PROD002"));
        verify(productRepository, times(1)).findById("PROD002");
    }
    
    @Test
    void testSaveProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product result = productService.saveProduct(product);
        assertNotNull(result);
        assertEquals("Neck Tshirt", result.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }
    
    @Test
    void testUpdateProduct() {
        when(productRepository.findById("PROD001")).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        product.setDescription("Men Short Sleves Tshirt");
        product.setPrice(599D);
        Product result = productService.updateProduct("PROD001", product);
        assertNotNull(result);
        assertEquals("Men Short Sleves Tshirt", result.getDescription());
        assertEquals(599D, result.getPrice());
        verify(productRepository, times(1)).findById("PROD001");
        verify(productRepository, times(1)).save(any(Product.class));
        verify(rabbitTemplate, times(1)).convertAndSend(ProductConstants.PRODUCT_EXCHANGE, 
        		ProductConstants.PRODUCT_UPDATED_ROUTE, result.getId());
    }
    
    @Test
    void testUpdateProductNotFound() {
        when(productRepository.findById("PROD002")).thenReturn(Optional.empty());
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> 
            productService.updateProduct("PROD002", product));
        assertEquals("Product does not exist with ID: PROD002", exception.getMessage());
        verify(productRepository, times(1)).findById("PROD002");
    }
    
    @Test
    void testDeleteProduct() {
    	when(productRepository.findById("PROD001")).thenReturn(Optional.of(product));
    	doNothing().when(productRepository).deleteById("PROD001");
        productService.deleteProductById("PROD001");
        verify(productRepository, times(1)).deleteById("PROD001");
    }
    
    @Test
    void testDeleteProductNotFound() {
    	when(productRepository.findById("PROD001")).thenReturn(Optional.empty());
    	assertThrows(ProductNotFoundException.class, () -> productService.deleteProductById("PROD001"));
    	verify(productRepository, times(1)).findById("PROD001");
    }
    
    
}
