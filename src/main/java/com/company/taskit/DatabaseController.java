package com.company.taskit;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Service
public class DatabaseController{

    @Autowired
    TaskRepository service;

    /**
     * Method to create new task from string payload
     *
     * @param payload "{}"
     * @return
     * @throws ParseException
     * @throws JSONException
     */
    public TaskItem newTask(String payload) throws ParseException, JSONException {

            JSONObject obj = new JSONObject(payload);
            TaskItem task = new TaskItem(obj);
        return this.service.save(task);
    }

    //function that gets all the tasks and returns a string
    public String GetAllTasks(int page) throws JsonProcessingException {
        Pageable pageRequest = PageRequest.of(page, 10);
        ArrayList<TaskItem> itemList = (ArrayList<TaskItem>) service.findAll(pageRequest);
        String results = tasksToJSON(itemList);
        return results;
    }

    /**
     * Method that converts list of taskItems to json strings
     * and concatenates them to returning string
     *
     * @param list List of taskItems
     * @return returning String in JSON format
     */
    public String tasksToJSON(ArrayList<TaskItem> list) {
        ArrayList<String> jsonList = new ArrayList<String>();
        String jsonString = "";
        for(TaskItem task: list) {
            String itemStringified = task.toString();
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
//    public String searchTasks(String searchParams) throws JSONException, JsonProcessingException {
//        JSONObject obj = new JSONObject(searchParams);
//        String description = obj.get("description").toString();
//        String title = obj.get("title").toString();
//        String comments = obj.get("comments").toString();
//        int page = Integer.valueOf(obj.get("page_number").toString());
//        Pageable request = PageRequest.of(page, 10);
//        ArrayList<TaskItem> list = (ArrayList<TaskItem>) service.search(description, title, comments);
//        String tasks = tasksToJSON(list);
//        return tasks;
//    }

    /**
    function that creates JSONObjects from payload and requests service to delete tasks
    params: String "{id: 1}"
    returns: null
    */
    public void deleteTaskItems(String payload) throws JSONException{
        JSONObject obj = new JSONObject(payload);
        service.deleteById((long) obj.get("id"));
    }

    /**
     * Method updates multiple tasks with information from payload
     *
     * @param payload String representing multiple Task objects
     *                "[{'id': 1, 'comments': 'random comment'}]"
     * @return
     * @throws JSONException
     */
    public Iterable<TaskItem> updateTask(String payload) throws JSONException{
        JSONArray arr = new JSONArray(payload);
        ArrayList<TaskItem> list = new ArrayList<>();
        for(int i = 0; i < arr.length(); i++){
            JSONObject obj = (JSONObject) arr.get(i);
            TaskItem task = service.getById((long) obj.get("id"));
            task.setComments((String) obj.get("comments"));
            task.setDueDate((Date) obj.get("dueDate"));
            task.setTitle((String) obj.get("title"));
            task.setDescription((String) obj.get("description"));
            list.add(task);
        }
        return service.saveAll(list);
    }

    public String findTasks(String search) throws JSONException {
        JSONObject obj = new JSONObject(search);
        String description = obj.get("description").toString();
        String title = obj.get("title").toString();
        String comments = obj.get("comments").toString();
        int page = Integer.valueOf(obj.get("page_number").toString());
        Pageable pageRequest = PageRequest.of(page, 10);
        ArrayList<TaskItem> list = (ArrayList<TaskItem>) service.search(description, title, comments, pageRequest);
        return tasksToJSON(list);
    }

}
