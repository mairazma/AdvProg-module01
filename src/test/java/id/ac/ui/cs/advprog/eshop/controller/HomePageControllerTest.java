package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HomePageControllerTest {
    private HomePageController homePageController;

    @BeforeEach
    void setUp() {
        homePageController = new HomePageController();
    }

    @Test
    void testHomePage() {
        String view = homePageController.homePage();
        assertEquals("HomePage", view);
    }
}