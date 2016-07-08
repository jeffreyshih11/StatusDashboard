package com.iworkscorp.dashboard.hudson.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by tmadison on 7/7/16.
 */
public class StatusScraper {
    public int test(String url) {
        Document doc;

        try {
            // need http protocol
            doc = Jsoup.connect(url).get();

            System.out.println(this.docToString(doc));
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        return 0;
    }

    private String docToString(Document document) {
        StringBuilder stringBuilder = new StringBuilder();

        // get page title
        stringBuilder.append("title : " + document.title());

        // get all links
        Elements links = document.select("a[href]");
        for (Element link : links) {
            // get the value from href attribute
            stringBuilder.append("\n\nlink : " + link.attr("href"));
            stringBuilder.append("\ntext : " + link.text());
        }

        return stringBuilder.toString();
    }
}
