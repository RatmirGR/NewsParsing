package com.ereed.webservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Article")
public class Article {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "article_title")
    private String articleTitle;

    @Column(name = "link_to_article")
    private String linkToArticle;

    @Column(name = "link_to_comments")
    private String linkToComments;

    @Column(name = "score")
    private int score;

    @Column(name = "number_of_comments")
    private int numberOfComments;

    @Column(name = "date_publication")
    private String datePublication;

}
