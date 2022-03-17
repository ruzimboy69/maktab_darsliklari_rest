package com.example.maktab_darsliklari_rest.repository;


import com.example.maktab_darsliklari_rest.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    boolean existsByName(String name);
}
