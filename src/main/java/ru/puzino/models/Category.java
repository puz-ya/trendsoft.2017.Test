package ru.puzino.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Puzino Yury on 27.02.2017.
 */
public class Category {

    /** id of category (unique) */
    private Long mId;
    /** name of category */
    private String mCatName;
    /** set of news in this category */
    private Set<News> mNews = new HashSet<>();

    public Category(Long id, String name){
        this.mId = id;
        this.mCatName = name;
    }

    @Override
    public String toString(){
        return "Category{" + "mId = " + mId + ", mCatName = '" + mCatName + "'" + "}";
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getmCatName() {
        return mCatName;
    }

    public void setmCatName(String mCatName) {
        this.mCatName = mCatName;
    }

    public Set<News> getmNews() {
        return mNews;
    }

    public void setmNews(Set<News> mNews) {
        this.mNews = mNews;
    }
}
