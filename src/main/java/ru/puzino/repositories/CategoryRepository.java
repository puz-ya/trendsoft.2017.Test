package ru.puzino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.puzino.models.Category;

/**
 * Created by Puzino Yury on 28.02.2017.
 * Empty.
 */
@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {

}
