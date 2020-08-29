package com.company.taskit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.JSONArray;

import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;

public class DatabaseController{

    //create private instance of repository
    private TaskManager tm;

    //function that gets the raw payload and create a task item
    public List<TaskItem> newTask(String payload) throws ParseException {
        JSONArray arr = new JSONArray(payload);
        List<TaskItem> list = new ArrayList<TaskItem>();
        for(int i = 0; i < arr.length(); i++){
            JSONObject obj = arr.getJSONObject(i);
            TaskItem task = new TaskItem(obj);
            list.add(task);
        }
//      tm.saveAll(list);
        return list;
    }

    //function that gets all the tasks and returns a string
    public String GetAllTasks() throws JsonProcessingException {
        ArrayList<TaskItem> itemList = (ArrayList<TaskItem>) tm.findAll();
        String results = tasksToJSON(itemList);
        return results;
    }

    //function that converts the
    public String tasksToJSON(ArrayList<TaskItem> list) throws JsonProcessingException {
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

    /*
    function that gets keys and values for search params
    params: String "[{key:value}]"
    returns: List<TaskItem>
    */
    public List<TaskItem> getTasks(String searchParams){
        JSONArray arr = new JSONArray(searchParams);
        List<TaskItem> list = tm.filterTasks(arr);
        return list;
    }

}
