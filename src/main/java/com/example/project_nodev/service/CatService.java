package com.example.project_nodev.service;


import com.example.project_nodev.entity.Cat;
import com.example.project_nodev.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {

    @Autowired
    private CatRepository catRepository;

    public void write(Cat cat){

        catRepository.save(cat);
    }

    public List<Cat> catList(){
        return catRepository.findAll();
    }
}
