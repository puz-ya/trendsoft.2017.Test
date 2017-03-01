package ru.puzino.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.puzino.bindingModel.NewsBinding;
import ru.puzino.models.Category;
import ru.puzino.models.News;
import ru.puzino.repositories.CategoryRepository;
import ru.puzino.repositories.NewsRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Puzino Yury on 28.02.2017.
 */

@Controller
public class NewsController {

    @Autowired
    private NewsRepository newsRepo;
    @Autowired
    private CategoryRepository catRepo;

    /** set form to fill */
    @GetMapping("/news/create")
    public String create(Model model){
        List<Category> list = this.catRepo.findAll();
        model.addAttribute("categories", list);
        return "news/create";
    }

    /** get POST answer from form */
    @PostMapping("/news/create")
    public String createProcess(NewsBinding newsBinding){
        //small filter input
        String title = newsBinding.getTitle();
        String content = newsBinding.getContent();
        if(title.isEmpty() || content.isEmpty()){
            return "redirect:/";
        }
        Category cat = this.catRepo.findOne(newsBinding.getCategoryId());
        News news = new News(
                title,
                content,
                cat
        );
        this.newsRepo.saveAndFlush(news);
        return "redirect:/";
    }

    /** edit news form */
    @GetMapping("/news/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){

        if(!this.newsRepo.exists(id)){
            return "redirect:/";
        }
        News news = this.newsRepo.findOne(id);
        List<Category> list = this.catRepo.findAll();

        model.addAttribute("categories", list);
        model.addAttribute("news", news);
        return "news/edit";
    }

    /** get POST answer from edit */
    @PostMapping("/news/edit/{id}")
    public String editProcess(@PathVariable("id") Long id, NewsBinding newsBinding){

        if(!this.newsRepo.exists(id)){
            return "redirect:/";
        }

        News news = this.newsRepo.findOne(id);
        news.setTitle(newsBinding.getTitle());
        news.setContent(newsBinding.getContent());
        Category cat = this.catRepo.findOne(newsBinding.getCategoryId());
        news.setCategory(cat);
        //update time
        LocalDateTime localTime = LocalDateTime.now();
        news.setTime(localTime);

        this.newsRepo.saveAndFlush(news);
        return "redirect:/news/" + news.getId();
    }

    /** delete news form */
    @GetMapping("/news/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model){

        if(!this.newsRepo.exists(id)){
            return "redirect:/";
        }
        News news = this.newsRepo.findOne(id);
        model.addAttribute("news", news);
        return "news/delete";
    }

    /** get POST answer from delete */
    @PostMapping("/news/delete/{id}")
    public String deleteProcess(@PathVariable("id") Long id, NewsBinding newsBinding){

        if(!this.newsRepo.exists(id)){
            return "redirect:/";
        }

        News news = this.newsRepo.findOne(id);
        this.newsRepo.delete(news);
        return "redirect:/news/admin";
    }

    /** show news by ID */
    @RequestMapping({"/news/view/{id}", "/news/{id}"})
    public String view(@PathVariable("id") Long id, Model model) {

        if(!this.newsRepo.exists(id)){
            return "redirect:/";
        }
        News news = this.newsRepo.findOne(id);
        model.addAttribute("news", news);
        return "news/view";
    }

    @RequestMapping({"/news/admin", "/news/admin/all"})
    public String all_news(Model model) {
        List<News> newsList = this.newsRepo.findAllNews();
        model.addAttribute("all_news", newsList);
        return "news/view_all_admin";
    }

    @RequestMapping({"/news", "/news/all", "/news/view/all"})
    public String all_news_view(Model model) {
        List<News> newsList = this.newsRepo.findAllNews();
        model.addAttribute("all_news", newsList);
        return "news/view_all";
    }
}