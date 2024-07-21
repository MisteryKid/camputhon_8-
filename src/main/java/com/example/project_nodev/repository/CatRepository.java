package com.example.project_nodev.repository;

import com.example.project_nodev.entity.Cat;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.security.auth.Subject;
import java.util.List;


@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {

    Page<Cat> findByTitleContaining(String searchKeyword, Pageable pageable);

    Cat findByTitleAndContent(Subject title, String content);

    List<Cat> findByContentContaining(String content);
}
