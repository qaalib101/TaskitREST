package com.company.taskit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Repository
@Transactional(readOnly = true)
public class TaskManager{
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TaskRepository repository;

    private QTaskItem task = QtaskItem.taskItem;

    public List<TaskItem> findByName(String name){
        return repository.findAll(task.name.contains(name));
    }

    public TaskItem save(TaskItem task){
        return repository.save(task);
    }

    public Iterable<TaskItem> findAll(){
        return repository.findAll();
    }

    public List<TaskItem> filterTasks(JSONArray arr){
        String where = getWhereQuery(arr);
        TypedQuery query = em.createQuery("select t from TaskItem t where" + where, TaskItem.class);
        List<TaskItem> list = query.getResultList();
        return list;
    }

    public String getWhereQuery(JSONArray arr){
        String whereQuery = "";
        for(Object obj: arr){
            JSONObject json = new JSONObject(obj);
            Set keys = (Set) json.keys();
            for(Object key: keys){
                String clause = "";
                String value = (String) json.get((String) key);
                if(keys.){
                    clause += " " + key + " = " + value;
                }
                whereQuery += clause;
            }
        }
    }
}
