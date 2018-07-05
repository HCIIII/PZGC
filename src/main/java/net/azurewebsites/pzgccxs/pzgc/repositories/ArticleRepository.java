package net.azurewebsites.pzgccxs.pzgc.repositories;

import net.azurewebsites.pzgccxs.pzgc.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

}
