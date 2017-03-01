package ru.puzino.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.puzino.bindingModel.CategoryBinding;
import ru.puzino.models.Category;
import ru.puzino.models.News;
import ru.puzino.repositories.CategoryRepository;
import ru.puzino.repositories.NewsRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Puzino Yury on 28.02.2017.
 */

@Controller
public class CategoryController {

    @Autowired
    private NewsRepository newsRepo;
    @Autowired
    private CategoryRepository catRepo;

    /** set form to fill */
    @GetMapping("/category/create")
    public String create(){
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

    /** edit category form */
    @GetMapping("/category/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        if(!this.catRepo.exists(id)){
            return "redirect:/";
        }
        Category category = this.catRepo.findOne(id);
        model.addAttribute("category", category);
        return "category/edit";
    }

    /** get POST answer from edit */
    @PostMapping("/category/edit/{id}")
    public String editProcess(@PathVariable("id") Long id, CategoryBinding categoryBinding){
        //insert filter here
        String name = categoryBinding.getName();
        if(name.isEmpty()){
            //error
            return "redirect:/";
        }

        Category category = this.catRepo.findOne(id);
        category.setName(categoryBinding.getName());
        this.catRepo.saveAndFlush(category);
        return "redirect:/category/admin";
    }

    /** delete category form */
    @GetMapping("/category/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        if(!this.catRepo.exists(id)){
            return "redirect:/";
        }
        Category category = this.catRepo.findOne(id);
        model.addAttribute("category", category);
        return "category/delete";
    }

    /** get POST answer from delete */
    @PostMapping("/category/delete/{id}")
    public String deleteProcess(@PathVariable("id") Long id, CategoryBinding categoryBinding){

        if(!this.catRepo.exists(id)){
            //error
            return "redirect:/";
        }

        Category category = this.catRepo.findOne(id);
        List<News> news = this.newsRepo.findByCategoryId(id);
        //we must delete all news in this category
        for(News n : news){
            this.newsRepo.delete(n.getId());
        }
        this.catRepo.delete(category);
        return "redirect:/category/admin";
    }

    /** show all news with this category ID */
    @RequestMapping("/category/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Category category = catRepo.findOne(id);
        if (category == null) {
            return "redirect:/";
        }

        List<News> news = newsRepo.findByCategoryId(id);
        news = news.stream()
                .sorted((a, b) -> b.getTime().compareTo(a.getTime()))
                .collect(Collectors.toList());
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
