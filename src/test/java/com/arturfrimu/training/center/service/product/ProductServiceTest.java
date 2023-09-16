package com.arturfrimu.training.center.service.product;

import com.arturfrimu.training.center.domain.product.Product;
import com.arturfrimu.training.center.repository.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("db-h2")
class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @BeforeEach
    void prepareDatabase() {
        when(productRepository.findAll()).thenReturn(productsToSave);
    }

    @Test
    void findProductById() {
        Product productById = productService.findProductById(1L);

        assertThat(productById)
                .usingRecursiveComparison()
                .isEqualTo(IPHONE_X);
    }

    private static final Product IPHONE_X = new Product(1L, "Iphone X", "The best phone", BigDecimal.valueOf(1000), 30);
    private static final Product IPHONE_14_PRO = new Product(2L, "Iphone 14 PRO", "The most expensive phone", BigDecimal.valueOf(1400), 25);
    private static final Product SAMSUNG_GALAXY_S20 = new Product(3L, "Samsung Galaxy S20", "The worst phone", BigDecimal.valueOf(1101), 5);
    private static final Product IPHONE_12_MINI = new Product(4L, "Iphone 12 Mini", "Compact and powerful", BigDecimal.valueOf(800), 20);
    private static final Product IPHONE_11_PRO = new Product(5L, "Iphone 11 PRO", "Professional-grade camera", BigDecimal.valueOf(950), 15);
    private static final Product SAMSUNG_GALAXY_S21 = new Product(6L, "Samsung Galaxy S21", "Next-gen power", BigDecimal.valueOf(1200), 10);
    private static final Product GOOGLE_PIXEL_5 = new Product(7L, "Google Pixel 5", "Pure Android experience", BigDecimal.valueOf(899), 8);
    private static final Product ONEPLUS_9_PRO = new Product(8L, "OnePlus 9 Pro", "Smooth performance", BigDecimal.valueOf(1099), 7);
    private static final Product HUAWEI_P40_PRO = new Product(9L, "Huawei P40 Pro", "Stunning photography", BigDecimal.valueOf(1100), 12);
    private static final Product XIAOMI_MI_11 = new Product(10L, "Xiaomi Mi 11", "High value for price", BigDecimal.valueOf(749), 18);

    private static final List<Product> productsToSave = List.of(
            IPHONE_X,
            IPHONE_14_PRO,
            SAMSUNG_GALAXY_S20,
            IPHONE_12_MINI,
            IPHONE_11_PRO,
            SAMSUNG_GALAXY_S21,
            GOOGLE_PIXEL_5,
            ONEPLUS_9_PRO,
            HUAWEI_P40_PRO,
            XIAOMI_MI_11
    );
}