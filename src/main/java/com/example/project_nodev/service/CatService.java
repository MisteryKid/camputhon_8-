package com.example.project_nodev.service;


import com.example.project_nodev.entity.Cat;
import com.example.project_nodev.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class CatService {

    @Autowired
    private CatRepository catRepository;

    public void write(Cat cat, @RequestParam(name="file", required = false) MultipartFile file)throws Exception{


        //저장경로 지정
        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files\\";


        UUID uuid = UUID.randomUUID();

        String filename = uuid +"_"+file.getOriginalFilename();

        File saveFile = new File(projectPath, filename);

        file.transferTo(saveFile);


        cat.setFilename(filename);
        cat.setFilepath("/files/"+filename);


        catRepository.save(cat);
    }

    public Page<Cat> catList(Pageable pageable){
        return catRepository.findAll(pageable);
    }

    public Page<Cat> catSearchList(String searchKeyword, Pageable pageable){

        return catRepository.findByTitleContaining(searchKeyword,pageable);
    }

    //특정 게시글 불러오기
    public Cat catView(Integer id) {

        return catRepository.findById(id).get();

    }
    //특정 게시글 삭제

    public void catDelete(Integer id) {
        catRepository.deleteById(id);
    }
}
