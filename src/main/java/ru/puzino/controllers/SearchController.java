package ru.puzino.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.puzino.bindingModel.SearchBigBinding;
import ru.puzino.bindingModel.SearchSmallBinding;
import ru.puzino.models.Category;
import ru.puzino.models.News;
import ru.puzino.repositories.CategoryRepository;
import ru.puzino.repositories.NewsRepository;

import java.util.List;

/**
 * Created by Puzino Yury on 01.03.2017.
 */
@Controller
public class SearchController {

    @Autowired
    private NewsRepository newsRepo;
    @Autowired
    private CategoryRepository catRepo;


    @RequestMapping("/search")
    public String search(Model model){
        //show all categories in search
        List<Category> categories = catRepo.findAll();
        model.addAttribute("categories", categories);
        return "search/index";
    }

    @PostMapping("/search/fullsearch")
    public String fullsearch(SearchBigBinding searchBinding, Model model){
        String tmpSearch = searchBinding.getSearchFullText();
        if(tmpSearch.isEmpty()){
            return "search/index";
        }

        //we using LIKE, so, need to %% our search string
        String keyword = "%" + tmpSearch + "%";
        //show all news which title & content matches
        List<News> searchNews = newsRepo.findFullSearchNews(keyword);
        model.addAttribute("news", searchNews);
        return "search/result";
    }

    @PostMapping("/search/partsearch")
    public String partsearch(SearchSmallBinding searchSmallBinding, Model model){
        String tmpTitle = searchSmallBinding.getSearchTitle();
        String tmpContent = searchSmallBinding.getSearchContent();
        Long catId = searchSmallBinding.getCategoryId();

        if(tmpTitle.isEmpty() || tmpContent.isEmpty() || catId < 0 || !this.catRepo.exists(catId)){
            return "search/index";
        }

        //we using LIKE, so, need to %% our search string
        String keyTitle = "%" + tmpTitle + "%";
        String keyContent = "%" + tmpContent + "%";
        //show all news which title & content matches
        List<News> searchNews = newsRepo.findPartSearchNews(keyTitle, keyContent, catId);
        model.addAttribute("news", searchNews);
        return "search/result";
    }
}
