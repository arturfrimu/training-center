package com.arturfrimu.training.center.service.product;

import com.arturfrimu.training.center.domain.product.Product;
import com.arturfrimu.training.center.exception.ProductNotInStockException;
import com.arturfrimu.training.center.exception.ResourceNotFoundException;
import com.arturfrimu.training.center.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.partitioningBy;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product findProductById(long id) {
        List<Product> allProducts = productRepository.findAll();

        return allProducts.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: %s".formatted(id)));
    }

    public List<Product> findAllIPhones() {
        List<Product> allProducts = productRepository.findAll();

        return allProducts.stream()
                .filter(product -> product.getName().toUpperCase().contains("IPHONE"))
                .toList();
    }

    public List<Product> findAllProductsCostingMoreThanGivenAmount(BigDecimal amount) {
        List<Product> allProducts = productRepository.findAll();

        return allProducts.stream()
                .filter(product -> product.getPrice().compareTo(amount) > 0)
                .toList();
    }

    public BigDecimal calculateSumOfProductPricesByTheirId(Long... ids) {
        List<Product> allProducts = productRepository.findAll();

        return allProducts.stream()
                .filter(product -> Arrays.asList(ids).contains(product.getId()))
                .map(Product::getPrice)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }

    public Optional<Product> whatIsTheMostExpensiveProduct() {
        List<Product> allProducts = productRepository.findAll();

        BinaryOperator<Product> productBinaryOperator = (currentProduct, nextProduct) -> currentProduct.getPrice().compareTo(nextProduct.getPrice()) > 0 ? currentProduct : nextProduct;

        return allProducts.stream().reduce(productBinaryOperator);
    }

    public Product byeProduct(Product product) {
        List<Product> allProducts = productRepository.findAll();

        Product productById = allProducts.stream()
                .filter(currentProduct -> currentProduct.getId().equals(product.getId()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: %s".formatted(product.getId())));

        if (productById.getStockQuantity() > 0) {
            productById.setStockQuantity(productById.getStockQuantity() - 1);
            return productById;
        }

        throw new ProductNotInStockException("This product with id: %s is not in stock".formatted(product.getId()));
    }

    public Map<Boolean, List<Product>> partitionProductsByPrice(BigDecimal threshold) {
        List<Product> allProducts = productRepository.findAll();

        return allProducts.stream()
                .collect(partitioningBy(product -> product.getPrice().compareTo(threshold) >= 0));
    }

    public Optional<Product> findTheProductWithTheBiggestNameLength() {
        return Optional.empty();
    }
}
