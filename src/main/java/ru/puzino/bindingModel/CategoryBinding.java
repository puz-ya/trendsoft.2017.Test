package ru.puzino.bindingModel;

import javax.validation.constraints.NotNull;

/**
 * Created by Puzino Yury on 28.02.2017.
 * To validate data from category forms
 */
public class CategoryBinding {

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
