package com.ereed.webservice.repositories;

import com.ereed.webservice.models.Article;
import com.ereed.webservice.models.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticlesRepository extends JpaRepository<Article, Integer> {
    List<Article> findByDatePublication(Date date);
}
