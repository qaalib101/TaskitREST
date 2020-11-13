package com.company.taskit;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.json.JSONObject;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;

@Repository
@Transactional(readOnly = true)
public interface TaskRepository extends JpaRepository<TaskItem, Long>{

    @Query("select t from TaskItem t where t.description like ?1 and t.title = ?2 and t.comments = ?3")
    public Iterable<TaskItem> search(String d, String t, String c);
}
