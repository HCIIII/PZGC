package net.azurewebsites.pzgccxs.pzgc.repositories;

import net.azurewebsites.pzgccxs.pzgc.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
