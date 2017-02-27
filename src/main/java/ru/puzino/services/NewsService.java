package ru.puzino.services;

import ru.puzino.models.Category;
import ru.puzino.models.News;

import java.util.List;

/**
 * Created by Puzino Yury on 27.02.2017.
 */
public interface NewsService {

    List<News> findAllNews();
    List<Category> findAllCategories();
    News findNewsById(Long id);
    News findNewsByCategory(Category category);

    News create(News news);
    News edit(News news);
    void deleteById(Long id);
}
