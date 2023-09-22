package com.arturfrimu.training.center.streams.repository.product;

import com.arturfrimu.training.center.streams.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
