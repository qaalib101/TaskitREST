package com.company.taskit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;

public class DatabaseController{

    //create private instance of repository
    private TaskRepository repo;

    //function that gets the raw payload and create a task item
    public TaskItem newTask(String payload){
        JSONObject obj = new JSONObject(payload);
        System.out.println(obj);
        TaskItem task = new TaskItem();
//        repo.save(task);
        return task;
    }

    //function that gets all the tasks and returns a string
    public String GetTasks() throws JsonProcessingException {
        Iterable<TaskItem> itemList = repo.findAll();
        String results = tasksToJSON(itemList);
        return results;
    }

    //function that converts the
    public String tasksToJSON(Iterable<TaskItem> list) throws JsonProcessingException {
        JSONObject obj = new JSONObject();
        ObjectMapper map = new ObjectMapper();
        ArrayList<String> jsonList = new ArrayList<String>();
        String jsonString = "";
        for(TaskItem task: list) {
            String itemStringified = map.writeValueAsString(task);
            jsonList.add(itemStringified);
        }
        jsonString = String.join(",", jsonList);
        return jsonString;
    }
}
