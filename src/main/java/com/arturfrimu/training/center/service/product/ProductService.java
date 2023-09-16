package com.arturfrimu.training.center.service.product;

import com.arturfrimu.training.center.domain.product.Product;
import com.arturfrimu.training.center.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product findProductById(long id) {
        List<Product> allProducts = productRepository.findAll();

        return allProducts.stream().filter(product -> product.getId().equals(id)).findFirst().orElseThrow(() -> new IllegalArgumentException(""));
    }
}
