package ru.puzino.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Puzino Yury on 27.02.2017.
 */
public class News {

    /** id (unique) */
    private Long mId;
    /** news title */
    private String mTitle;
    /** news text */
    private String mContent;
    /** news category (only one) */
    private Category mCategory;
    /** news date */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime mTime;

    public News(){
        mTime = LocalDateTime.now();
    }

    public News(Long id, String title, String content, Category category){
        this.mId = id;
        this.mTitle = title;
        this.mContent = content;
        this.mCategory = category;
        mTime = LocalDateTime.now();
    }

    @Override
    public String toString(){
        return "News{" + "mId = " + mId + ", mTitle = '" + mTitle
                + "', mContent = '" + mContent + "', mCategory = '" + mCategory.toString() + "'}";
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public Category getmCategory() {
        return mCategory;
    }

    public void setmCategory(Category mCategory) {
        this.mCategory = mCategory;
    }

    public LocalDateTime getmTime() {
        return mTime;
    }

    public String getmTimeString(){
        String str = mTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return str;
    }

    public void setmTime(LocalDateTime mTime) {
        this.mTime = mTime;
    }

    public void setmTime(String str){
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        this.mTime = LocalDateTime.parse(str, sdf);
    }
}
