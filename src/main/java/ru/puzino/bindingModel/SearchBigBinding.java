package ru.puzino.bindingModel;

import javax.validation.constraints.NotNull;

/**
 * Created by Puzino Yury on 28.02.2017.
 * To validate data from search form - long single <input>
 */
public class SearchBigBinding {

    @NotNull
    private String searchFullText;

    public String getSearchFullText() {
        return searchFullText;
    }

    public void setSearchFullText(String searchFullText) {
        this.searchFullText = searchFullText;
    }
}
