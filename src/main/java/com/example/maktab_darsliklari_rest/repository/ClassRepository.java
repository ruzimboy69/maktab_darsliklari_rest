package com.example.maktab_darsliklari_rest.repository;

import com.example.maktab_darsliklari_rest.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Classes, Integer> {
}
