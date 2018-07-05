package net.azurewebsites.pzgccxs.pzgc.controllers;

import net.azurewebsites.pzgccxs.pzgc.models.Article;
import net.azurewebsites.pzgccxs.pzgc.repositories.ArticleRepository;
import net.azurewebsites.pzgccxs.pzgc.utilities.ArticleListGenerator;
import net.azurewebsites.pzgccxs.pzgc.utilities.BlogGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/generateArticles")
    public String generateArticles() throws IOException {
        String parentPath = System.getProperty("user.dir");
        String blogTemplatePath = parentPath + "\\src\\main\\resources\\static\\blogs\\blog_template.html";
        String folderPath = parentPath + "\\src\\main\\resources\\static\\blogs\\";
        String newsTemplatePath = parentPath + "\\src\\main\\resources\\static\\blogs\\news_template.html";
        String path = parentPath + "\\src\\main\\resources\\static\\news.html";
        StringBuilder res = new StringBuilder();

        //生成blogs
        List<Article> articles = articleRepository.findAll(Sort.by(Sort.Direction.DESC, "postedAt"));
        Map<String, Article> filenameAndArticle = new LinkedHashMap<>(articles.size());
        for (Article article : articles) {
            String blogPath = BlogGenerator.generate(article, blogTemplatePath, folderPath);
            filenameAndArticle.put(BlogGenerator.getArticleStoredFilename(article), article);
            res.append(new Date().toString() + " ");
            res.append("Generated: " + blogPath + "<br/>");
        }

        //生成articleList
        String articleListPath = ArticleListGenerator.generate(filenameAndArticle, newsTemplatePath, path);
        res.append(new Date().toString() + " ");
        res.append("Generated: " + articleListPath + "<br/>");

        return res.toString();
    }
}
