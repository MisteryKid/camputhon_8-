package com.example.project_nodev.repository;

import com.example.project_nodev.entity.Cat;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Integer> {

}
