package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String view = productController.createProductPage(model);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
        assertEquals("createProduct", view);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        product.setProductName("Test");
        product.setProductQuantity(50);
        String view = productController.createProductPost(product, model);
        verify(productService).create(product);
        assertEquals("redirect:list", view);
    }

    @Test
    void testProductListPage() {
        List<Product> products = new ArrayList<>();
        when(productService.findAll()).thenReturn(products);
        String view = productController.productListPage(model);
        verify(model, times(1)).addAttribute("products", products);
        assertEquals("productList", view);
    }

    @Test
    void testEditProductPage() {
        Product product = new Product();
        when(productService.findById("1")).thenReturn(product);

        String view = productController.editProductPage("1", model);
        verify(model, times(1)).addAttribute("product", product);
        assertEquals("editProduct", view);
    }


    @Test
    void testEditProductPost() {
        Product product = new Product();
        product.setProductId("1");
        String view = productController.editProductPost(product, model);
        verify(productService, times(1)).edit("1", product);
        assertEquals("redirect:list", view);
    }

    @Test
    void testDeleteProduct() {
        String view = productController.deleteProduct("1");
        verify(productService, times(1)).delete("1");
        assertEquals("redirect:list", view);
    }
}