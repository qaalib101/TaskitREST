package com.company.taskit;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


public class TaskList{
    public interface TaskRepository extends CrudRepository<TaskList, Long>{

    }
}
