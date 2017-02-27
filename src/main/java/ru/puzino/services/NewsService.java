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
    List<News> findLatest5News();
    News findNewsById(Long id);
    List<News> findNewsByCategory(Category category);

    News create(News news);
    News edit(News news);
    void deleteById(Long id);
}
