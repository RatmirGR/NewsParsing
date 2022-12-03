package com.ereed.webservice.utils;

import com.ereed.webservice.models.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Parsing {

    private String url;

    public Parsing(String strDate) {
        this.url = "https://news.ycombinator.com/front?day="+strDate;
    }

    public void parse(){

        try {
            Document document = Jsoup.connect(url)
                    .referrer("https://www.google.com/")
                    .timeout(12000)
                    .get();

            Elements elements = document.select("#hnmain > tbody > tr:nth-child(3) > td > table");
            for (Element element: elements){

            }

//            Elements elements = document.getElementsByClass("titleline");
//            for (Element element : elements) {
//                String title = Objects.requireNonNull(element.select("a").first()).text();
//                System.out.println("title: " +title);
//                String linkToArticles = element.select("a").attr("href");
//                System.out.println("linkToArticles: " +linkToArticles);
//                System.out.println("------------------------------------");
//                break;
//            }
//
//            elements = document.getElementsByClass("subline");
//            for (Element element : elements){
//                int score = Integer.parseInt(element.getElementsByClass("score").text().split(" ")[0]);
//                System.out.println("score: "+score);
//                String linkToComments = element.select("a").attr("href");
//                System.out.println(linkToComments);
//                System.out.println("------------------------------------");
//                break;
//            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Article> getListArticles(){
        return new ArrayList<>();
    }
}
