package ru.puzino.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.puzino.models.News;

import java.util.List;

/**
 * Created by Puzino Yury on 28.02.2017.
 * "Magick" FindByColumn() -> sql SELECT ... FROM ... WHERE ...=Column
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByCategoryId(Long id);

    //limit is prohibited ! :\
    @Query("SELECT n FROM News n ORDER BY time DESC")
    List<News> findLatestNews(Pageable pageable);

    //select all from news by time order
    @Query("SELECT n FROM News n ORDER BY time DESC")
    List<News> findAllNews();

    //select all with search query
    @Query("SELECT n FROM News n WHERE (title LIKE :str OR content LIKE :str) ORDER BY time DESC")
    List<News> findFullSearchNews(@Param("str") String str);

    //select all with search query
    @Query("SELECT n FROM News n WHERE ((title LIKE :tit OR content LIKE :con) AND category_id = :cid) ORDER BY time DESC")
    List<News> findPartSearchNews(@Param("tit") String title, @Param("con") String content, @Param("cid") Long catId);

}
