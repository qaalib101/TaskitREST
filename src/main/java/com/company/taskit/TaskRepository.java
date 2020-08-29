package com.company.taskit;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.json.JSONObject;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import java.util.function.Predicate;

@RepositoryRestResource
public interface TaskRepository extends PagingAndSortingRepository<TaskItem, Long>, QuerydslPredicateExecutor<TaskItem>{

}
