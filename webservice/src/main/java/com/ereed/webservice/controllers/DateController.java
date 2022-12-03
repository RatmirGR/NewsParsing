package com.ereed.webservice.controllers;

import com.ereed.webservice.models.Article;
import com.ereed.webservice.models.Date;
import com.ereed.webservice.services.ArticlesService;
import com.ereed.webservice.services.DateService;
import com.ereed.webservice.utils.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DateController {

    private final DateService dateService;
    private final ArticlesService articlesService;

    @Autowired
    public DateController(DateService dateService, ArticlesService articlesService) {
        this.dateService = dateService;
        this.articlesService = articlesService;
    }

    @PostMapping("/load")
    public ResponseEntity<HttpStatus> load(@RequestBody String strDate) throws JsonProcessingException {

        /* распарсиваем полученный json*/
        ObjectMapper mapper = new ObjectMapper();
        Date datePublication = mapper.readValue(strDate, Date.class);
   //     dateService.save(datePublication);


        /* парсинг по указанной дате и получения списка новостей*/
        Parsing parsing = new Parsing(datePublication.getDate());
        parsing.parse();


//        List<Article> articleList = parsing.getListArticles();
//        Article article;
//        for (int i = 1; i <= 2; i++) {
//            article = new Article();
//            article.setArticleTitle("title "+i);
//            article.setLinkToArticle("link article "+i);
//            article.setLinkToComments("link comments "+i);
//            article.setScore(10*i);
//            article.setNumberOfComments(100*i);
//            article.setDatePublication(datePublication.getDate());
//            articleList.add(article);
//        }

//        for (Article article1: articleList) {
//            articlesService.save(article1);
//        }

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<DateErrorResponse> handleDuplicateException(DateDuplicateException ignore){
        DateErrorResponse response = new DateErrorResponse("Date already exists");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<DateErrorResponse> handleEmptyException(DateInvalidException ignore){
        DateErrorResponse response = new DateErrorResponse("Date invalid");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
