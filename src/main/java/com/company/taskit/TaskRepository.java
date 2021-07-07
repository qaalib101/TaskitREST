package com.company.taskit;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.json.JSONObject;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

@Repository
@Transactional(readOnly = true)
public interface TaskRepository extends PagingAndSortingRepository<TaskItem, Long>{

    @Query("select t from TaskItem t where t.description like %:d% and t.title like %:t% and t.comments like %:c%")
    public Page<TaskItem> search(String d, String t, String c, Pageable pageRequest);

    public Iterable<TaskItem> findByCreationDateBetween(Date start, Date end);

    public Page<TaskItem> findAll(Pageable pageRequest);

    public TaskItem getById(long id);
}
