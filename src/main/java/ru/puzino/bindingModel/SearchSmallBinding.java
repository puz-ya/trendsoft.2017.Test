package ru.puzino.bindingModel;

import javax.validation.constraints.NotNull;

/**
 * Created by Puzino Yury on 28.02.2017.
 * To validate data from search "small" forms (title, content, category)
 */
public class SearchSmallBinding {

    @NotNull
    private String searchTitle;

    @NotNull
    private String searchContent;

    @NotNull
    private Long categoryId;

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
