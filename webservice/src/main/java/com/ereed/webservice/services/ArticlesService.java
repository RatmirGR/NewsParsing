package com.ereed.webservice.services;

import com.ereed.webservice.models.Article;
import com.ereed.webservice.models.Date;
import com.ereed.webservice.repositories.ArticlesRepository;
import com.ereed.webservice.repositories.DateRepository;
import com.ereed.webservice.utils.ArticleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ArticlesService {

    private final ArticlesRepository articlesRepository;
    private final DateRepository dateRepository;

    @Autowired
    public ArticlesService(ArticlesRepository articlesRepository, DateRepository dateRepository) {
        this.articlesRepository = articlesRepository;
        this.dateRepository = dateRepository;
    }

    public void save(Article article){
        articlesRepository.save(article);
    }


    public List<Article> findArticlesByDate(String strDate) throws ArticleNotFoundException{
        Optional<Date> foundDate = dateRepository.findByDate(strDate);
        if (foundDate.isEmpty())
            throw new ArticleNotFoundException();

        return articlesRepository.findByDatePublication(foundDate.get());
    }
}
