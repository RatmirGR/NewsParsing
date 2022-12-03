package com.ereed.webservice.controllers;

import com.ereed.webservice.models.Article;
import com.ereed.webservice.services.ArticlesService;
import com.ereed.webservice.utils.ArticleErrorResponse;
import com.ereed.webservice.utils.ArticleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ArticlesController {

    private final ArticlesService articlesService;

    @Autowired
    public ArticlesController(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }

    @ResponseBody
    @GetMapping("/info")
    public List<Article> info(@RequestParam(value = "date") String strDate){
        return articlesService.findArticlesByDate(strDate);
    }

    @ExceptionHandler
    private ResponseEntity<ArticleErrorResponse> handleException(ArticleNotFoundException ex){
        ArticleErrorResponse response = new ArticleErrorResponse("Articles with this date wasn't found!");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
