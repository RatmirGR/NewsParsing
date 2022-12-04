package com.ereed.webservice.utils;

import com.ereed.webservice.models.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Parsing {

    private final String url;
    private final List<Article> articles;

    public Parsing(String strDate) {
        this.url = "https://news.ycombinator.com/front?day="+strDate;
        articles = new ArrayList<>();
    }

    public void parse(){
        Article article;

        try {
            Document document = Jsoup.connect(url)
                    .referrer("https://www.google.com/")
                    .timeout(12000)
                    .get();

            Elements titleLine = document.select("#hnmain .titleline");
            Elements subLine = document.select("#hnmain .subline");

            if (titleLine.size() == subLine.size()){
                for (int i = 0; i < titleLine.size(); i++) {
                    article = new Article();
                    Element element1 = titleLine.get(i);
                    Element element2 = subLine.get(i);

                    article.setArticleTitle(Objects.requireNonNull(element1.select("a").first()).text());
                    article.setLinkToArticle(element1.select("a").attr("href"));
                    article.setLinkToComments(element2.select("a").last().attr("href"));
                    article.setScore(Integer.parseInt(element2.getElementsByClass("score").text().split(" ")[0]));
                    article.setNumberOfComments(Integer.parseInt(element2.select("a").last().text().split(" ")[0]));
                    articles.add(article);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Article> getListArticles(){
        return articles;
    }
}
