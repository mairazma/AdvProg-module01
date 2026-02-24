package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("1");
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        verify(productRepository).create(product);
        assertEquals("1", createdProduct.getProductId());
    }

    @Test
    void testFindAll() {
        List<Product> products = new ArrayList<>();
        Iterator<Product> iterator = products.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> retrievedProducts = productService.findAll();

        verify(productRepository).findAll();
        assertEquals(products, retrievedProducts);
    }

    @Test
    void testFindById() {
        Product product = new Product();
        when(productRepository.findById("1")).thenReturn(product);

        Product retrievedProduct = productService.findById("1");

        verify(productRepository).findById("1");
        assertEquals(product, retrievedProduct);
    }

    @Test
    void testEdit() {
        Product newProductData = new Product();
        newProductData.setProductId("1");

        when(productRepository.edit("1", newProductData)).thenReturn(newProductData);

        Product editedProduct = productService.edit("1", newProductData);

        verify(productRepository, times(1)).edit("1", newProductData);
        assertEquals("1", editedProduct.getProductId());
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete("1");

        productService.delete("1");

        verify(productRepository, times(1)).delete("1");
    }
}