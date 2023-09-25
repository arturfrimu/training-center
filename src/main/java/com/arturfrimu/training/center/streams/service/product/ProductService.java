package com.arturfrimu.training.center.streams.service.product;

import com.arturfrimu.training.center.streams.entity.product.Product;
import com.arturfrimu.training.center.streams.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product findProductById(long id) {
        List<Product> allProducts = productRepository.findAll();

        // TODO: 25.09.2023

        return null;
    }

    public List<Product> findAllIPhones() {
        List<Product> allProducts = productRepository.findAll();

        // TODO: 25.09.2023

        return emptyList();
    }

    public List<Product> findAllProductsCostingMoreThanGivenAmount(BigDecimal amount) {
        List<Product> allProducts = productRepository.findAll();

        // TODO: 25.09.2023

        return emptyList();
    }

    public BigDecimal calculateSumOfProductPricesByTheirId(Long... ids) {
        List<Product> allProducts = productRepository.findAll();

        // TODO: 25.09.2023

        return BigDecimal.ZERO;
    }

    public Optional<Product> whatIsTheMostExpensiveProduct() {
        List<Product> allProducts = productRepository.findAll();

        // TODO: 25.09.2023

        return empty();
    }

    public Product byeProduct(Product product) {
        List<Product> allProducts = productRepository.findAll();

        // TODO: 25.09.2023

        return null;
    }

    public Map<Boolean, List<Product>> partitionProductsByPrice(BigDecimal threshold) {
        List<Product> allProducts = productRepository.findAll();

        // TODO: 25.09.2023

        return Map.of();
    }

    public Optional<Product> findTheProductWithTheBiggestNameLength() {
        return empty();
    }
}
