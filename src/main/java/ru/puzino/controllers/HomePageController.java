package ru.puzino.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.puzino.models.Category;
import ru.puzino.models.News;
import ru.puzino.repositories.CategoryRepository;
import ru.puzino.repositories.NewsRepository;

import java.util.List;

/**
 * Created by Puzino Yury on 27.02.2017.
 */
@Controller
public class HomePageController {


    /** "Autowired" will automatically injects the correct implementation for our services */
    /*
    @Autowired
    private NewsService mNewsService;
    @Autowired
    private CategoryService mCatService;
    //*/

    @Autowired
    private NewsRepository newsRepo;
    @Autowired
    private CategoryRepository catRepo;

    @RequestMapping("/")
    public String index(Model model){

        //show last 5 news by date
        List<News> latest5News = newsRepo.findLatestNews(new PageRequest(0,5));
        model.addAttribute("latest5news", latest5News);

        //show all categories at the sidebar
        List<Category> categories = catRepo.findAll();
        model.addAttribute("categories", categories);

        return "index";
    }

    @RequestMapping("/search")
    public String search(){

        return "search";
    }
}
