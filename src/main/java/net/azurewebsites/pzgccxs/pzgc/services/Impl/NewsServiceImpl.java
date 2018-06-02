package net.azurewebsites.pzgccxs.pzgc.services.Impl;

import net.azurewebsites.pzgccxs.pzgc.models.News;
import net.azurewebsites.pzgccxs.pzgc.repositories.NewsRepository;
import net.azurewebsites.pzgccxs.pzgc.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
}
