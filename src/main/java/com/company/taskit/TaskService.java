package com.company.taskit;


import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mortbay.thread.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private QTaskItem task = QTaskItem.taskItem;

    //create private instance of repository
    @Autowired
    private TaskRepository repo;

    /* Search function that
    inputs JSONArray [{"title": "work on"}, {"description": "example"}]
    and returns
    * */
    public Iterable<TaskItem> search(JSONObject obj) throws JSONException {
        Predicate title = task.title.contains(obj.get("title").toString());
        Predicate description = task.description.contains(obj.get("description").toString());

        BooleanExpression exp = JPAExpressions.selectOne().from(task).where(
                title,description
        ).exists();

        return repo.findAll(exp);
    }

    public Iterable<TaskItem> findAll(){
        return repo.findAll();
    }

    public Iterable<TaskItem> saveAll(Iterable<TaskItem> list){
        return repo.saveAll(list);
    }

    public void deleteById(long id){
        repo.deleteById(id);
    }

    public TaskItem getOne(long id){
        return repo.getOne(id);
    }

    public long getCount(){
        return repo.count();
    }
}
