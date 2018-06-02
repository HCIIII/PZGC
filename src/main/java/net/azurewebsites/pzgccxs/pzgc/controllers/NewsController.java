package net.azurewebsites.pzgccxs.pzgc.controllers;

import net.azurewebsites.pzgccxs.pzgc.models.News;
import net.azurewebsites.pzgccxs.pzgc.services.NewsService;
import net.azurewebsites.pzgccxs.pzgc.utilities.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/all")
    public String getAllNews(){
        List<News> news = newsService.getAllNews();
        return JSONResult.fillResultString(200, "ok", news);
    }
}
