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

import static com.ereed.webservice.utils.JSONValidator.getStringJSONValid;


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
    public ResponseEntity<HttpStatus> load(@RequestBody(required = false) String strDate) {

        ObjectMapper mapper = new ObjectMapper();
        Date datePublication;
        try {
            strDate = getStringJSONValid(strDate);
            datePublication = mapper.readValue(strDate, Date.class);
        } catch (JsonProcessingException e) {
            throw new DateInvalidException();
        }
        dateService.save(datePublication);

        Parsing parsing = new Parsing(datePublication.getDate());
        parsing.parse();
        List<Article> articleList = parsing.getListArticles();

        for (Article article: articleList) {
            article.setDatePublication(datePublication);
            articlesService.save(article);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<DateErrorResponse> handleDuplicateException(DateDuplicateException ignore){
        DateErrorResponse response = new DateErrorResponse("Date already exists");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<DateErrorResponse> handleEmptyException(DateInvalidException ignore){
        DateErrorResponse response = new DateErrorResponse("Data invalid");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
