package ru.puzino.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.puzino.bindingModel.CategoryBinding;
import ru.puzino.bindingModel.NewsBinding;
import ru.puzino.models.Category;
import ru.puzino.models.News;
import ru.puzino.repositories.CategoryRepository;
import ru.puzino.repositories.NewsRepository;
import ru.puzino.services.CategoryService;
import ru.puzino.services.NewsService;

import java.util.List;

/**
 * Created by Puzino Yury on 28.02.2017.
 */

@Controller
public class CategoryController {

    //@Autowired
    //private NewsService postService;
    //@Autowired
    //private CategoryService catService;

    @Autowired
    private NewsRepository newsRepo;
    @Autowired
    private CategoryRepository catRepo;

    /** set form to fill */
    @GetMapping("/category/create")
    public String create(Model model){
        //just enter name of category
        return "category/create";
    }

    /** get POST answer from form */
    @PostMapping("/category/create")
    public String createProcess(CategoryBinding categoryBinding){
        //insert filter here
        String name = categoryBinding.getName();
        if(name.isEmpty()){
            //error
            return "redirect:/";
        }

        Category cat = new Category(name);
        this.catRepo.saveAndFlush(cat);
        return "redirect:/";
    }

    /** show all news with this category ID */
    @RequestMapping("/category/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Category category = catRepo.findOne(id);
        if (category == null) {
            return "redirect:/";
        }

        List<News> news = newsRepo.findByCategoryId(id);
        model.addAttribute("category", category);
        model.addAttribute("all_category_news", news);
        return "category/view_news";
    }

    /** show all categories in Admin mode */
    @RequestMapping({"/category/admin", "/category/admin/all"})
    public String all_cat(Model model) {
        List<Category> cats = catRepo.findAll();
        model.addAttribute("all_categories", cats);
        return "category/view_all_admin";
    }

    /** show all categories */
    @RequestMapping({"/category", "/category/all", "/category/view/all"})
    public String all_cat_view(Model model) {
        List<Category> cats = catRepo.findAll();
        model.addAttribute("all_categories", cats);
        return "category/view_all";
    }
    //*/
}
