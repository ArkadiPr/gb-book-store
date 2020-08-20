package com.geekbrains.book.store.services.analytic;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.MethodStat;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.repositories.MethodStatRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MethodStatService {
    private MethodStatRepo methodStatRepo;

    public MethodStat saveOrUpdate(MethodStat methodStat) {
        return methodStatRepo.save(methodStat);
    }

    public List<MethodStat> getAll(){
        return methodStatRepo.findAll();
    }

    public MethodStat findById(Long id) {
        return methodStatRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("MethodStat with id: " + id + " not found"));
    }

    public MethodStat getByMethodName(String methodName){
        return methodStatRepo.findByMethodName(methodName).orElseGet(() -> new MethodStat(methodName));
//        return methodStatRepo.findByMethodName(methodName).orElse(null);
    }
}
