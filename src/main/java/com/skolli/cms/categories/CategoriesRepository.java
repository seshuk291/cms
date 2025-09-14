package com.skolli.cms.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    @Query("select c from Categories c where c.id in (:categories)")
    Optional<List<Categories>> findByCategoriesIds(List<Long> categories);

    Optional<Categories> findCategoriesByName(String name);
}
