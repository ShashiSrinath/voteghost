package com.voteghost.repositories;

import com.voteghost.domain.Product;
import com.voteghost.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p join fetch p.user where p.id = ?1")
    Optional<Product> findByIdWithUser(Long id);

    List<Product> findByUser(User user);

    Optional<Product> findByName(String name);
}
