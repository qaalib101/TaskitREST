package com.company.taskit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Service
public class DatabaseController{

    @Autowired
    private TaskService service;

    //function that gets the raw payload and create a task item
    public List<TaskItem> newTask(String payload) throws ParseException, JSONException {
        JSONArray arr = new JSONArray(payload);
        List<TaskItem> list = new ArrayList<TaskItem>();
        for(int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            TaskItem task = new TaskItem(obj);
            list.add(task);
        }
        return (ArrayList<TaskItem>) service.saveAll(list);
    }

    //function that gets all the tasks and returns a string
    public String GetAllTasks() throws JsonProcessingException {
        ArrayList<TaskItem> itemList = (ArrayList<TaskItem>) service.findAll();
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
    params: String "{key:value, key: value}"
    returns: List<TaskItem>
    */
    public List<TaskItem> getTasks(String searchParams) throws JSONException {
        JSONObject obj = new JSONObject(searchParams);
        ArrayList<TaskItem> list = (ArrayList<TaskItem>) service.search(obj);
        return list;
    }

    /*
    function that creates JSONObjects from payload and requests service to delete tasks
    params: String "{id: 1}"
    returns: null
    */
    public void deleteTaskItems(String payload) throws JSONException{
        JSONObject obj = new JSONObject(payload);
        service.deleteById((long) obj.get("id"));
    }

    /*
    function gets task object and sets fields. Then save
    params: String "[{}]"
    */
    public Iterable<TaskItem> updateTask(String payload) throws JSONException{
        JSONArray arr = new JSONArray(payload);
        ArrayList<TaskItem> list = new ArrayList<>();
        for(int i = 0; i < arr.length(); i++){
            JSONObject obj = (JSONObject) arr.get(i);
            TaskItem task = service.getOne((long) obj.get("id"));
            task.setComments((String) obj.get("comments"));
            task.setDueDate((Date) obj.get("dueDate"));
            task.setTitle((String) obj.get("title"));
            task.setDescription((String) obj.get("description"));
            list.add(task);
        }
        return service.saveAll(list);
    }

}
