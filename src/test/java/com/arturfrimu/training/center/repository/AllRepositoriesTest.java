package com.arturfrimu.training.center.repository;

import com.arturfrimu.training.center.entity.address.Address;
import com.arturfrimu.training.center.entity.customer.Customer;
import com.arturfrimu.training.center.entity.order.Order;
import com.arturfrimu.training.center.entity.product.Product;
import com.arturfrimu.training.center.repository.address.AddressRepository;
import com.arturfrimu.training.center.repository.customer.CustomerRepository;
import com.arturfrimu.training.center.repository.order.OrderRepository;
import com.arturfrimu.training.center.repository.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("db-postgress-test")
class AllRepositoriesTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    void testEntitiesMapping() {
        var customerAddress = addressRepository.save(new Address());
        var shippingAddress = addressRepository.save(new Address());
        var customer = new Customer();
        customer.setCustomerAddress(customerAddress);
        var savedCustomer = customerRepository.save(customer);
        var savedProduct = productRepository.save(new Product());
        var order = new Order();
        order.setShippingAddress(shippingAddress);
        order.setCustomer(savedCustomer);
        order.addProduct(savedProduct);
        orderRepository.save(order);

        assertThat(addressRepository.findAll()).hasSize(2);
        assertThat(customerRepository.findAll()).hasSize(1);
        assertThat(productRepository.findAll()).hasSize(1);
        assertThat(orderRepository.findAll()).hasSize(1);
        assertThat(orderRepository.findAll().get(0).getProducts()).hasSize(1);
    }
}
