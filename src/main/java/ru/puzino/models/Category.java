package ru.puzino.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Puzino Yury on 27.02.2017.
 */

@Entity
@Table(name = "categories")
public class Category {

    /** id of category (unique) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** name of category */
    @Column(nullable = false, length = 300)
    private String name;

    /** all news by category */
    @OneToMany(mappedBy = "category")
    private List<News> news;

    public Category(){}

    public Category(String name){
        this.name = name;

        this.news = new ArrayList<>();
    }

    @Override
    public String toString(){
        return "Category{" + "id = " + id + ", name = '" + name + "'" + "}";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
