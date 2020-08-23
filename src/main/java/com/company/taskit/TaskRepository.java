package com.company.taskit;

import org.springframework.data.repository.CrudRepository;
import org.json.JSONObject;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TaskRepository extends CrudRepository<TaskItem, Long> {

}
