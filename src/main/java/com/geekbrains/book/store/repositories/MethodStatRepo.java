package com.geekbrains.book.store.repositories;

import com.geekbrains.book.store.entities.MethodStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MethodStatRepo extends JpaRepository<MethodStat, Long> {
    Optional<MethodStat> findByMethodName(String methodName);
}
