package ru.puzino.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Puzino Yury on 27.02.2017.
 * Model of News
 */
@Entity
@Table(name = "news")
public class News {

    /** id (unique) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** news title */
    @Column(nullable = false, length = 300)
    private String title;

    /** news text */
    @Lob
    @Column(nullable = false, columnDefinition = "text")
    private String content;

    /** news category (only one) */
    @ManyToOne()
    @JoinColumn(nullable = false, name="categoryId")
    private Category category;

    /** news date */
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime time;

    public News(){
        time = LocalDateTime.now();
    }

    public News(String title, String content, Category category){
        this.title = title;
        this.content = content;
        this.category = category;
        this.time = LocalDateTime.now();
    }

    @Override
    public String toString(){
        return "News{" + "id = " + id + ", title = '" + title
                + "', content = '" + content + "', category = '" + category.toString() + "'}";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortTitle() {
        String tmpTitle;
        if(title.length() > 20){
            tmpTitle = title.substring(0, 20) + "...";
        }else{
            tmpTitle = title;
        }
        return tmpTitle;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getShortContent() {
        String tmpContent;
        if(content.length() > 20){
            tmpContent = content.substring(0, 20) + "...";
        }else{
            tmpContent = content;
        }
        return tmpContent;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getmTimeString(){
        String str = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return str;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setTimeString(String str){
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.time = LocalDateTime.parse(str, sdf);
    }
}
