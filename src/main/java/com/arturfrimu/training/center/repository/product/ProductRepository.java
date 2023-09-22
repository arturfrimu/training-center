package com.arturfrimu.training.center.repository.product;

import com.arturfrimu.training.center.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
