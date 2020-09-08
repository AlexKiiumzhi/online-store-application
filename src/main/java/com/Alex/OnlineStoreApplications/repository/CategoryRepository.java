package com.Alex.OnlineStoreApplications.repository;

import com.Alex.OnlineStoreApplications.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Long> {
    @Query(value = "select id from category where name = :name ", nativeQuery = true)
    Long getCategoryId(@Param("name") String name);

}
