package com.example.maktab_darsliklari_rest.repository;

import com.example.maktab_darsliklari_rest.entity.Book;
import com.example.maktab_darsliklari_rest.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByDeviceId(String deviceId);
    boolean existsByBooks(Book book);

}
