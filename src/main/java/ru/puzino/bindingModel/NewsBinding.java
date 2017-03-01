package ru.puzino.bindingModel;

import javax.validation.constraints.NotNull;

/**
 * Created by Puzino Yury on 28.02.2017.
 * To validate data from news forms
 */
public class NewsBinding {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Long categoryId;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long category_id) {
        this.categoryId = category_id;
    }
}
