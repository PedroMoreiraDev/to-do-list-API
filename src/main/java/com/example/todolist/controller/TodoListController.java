package com.example.todolist.controller;

import com.example.todolist.model.TodoListModel;
import com.example.todolist.service.TodoListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TodoListController {

    TodoListService todoListService;

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoListModel createTask (@RequestBody TodoListModel task){
        return todoListService.createTask(task);
            }


            @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TodoListModel> getAllTasks(){
        return todoListService.listAllTasks();
            }

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TodoListModel> getTaskById(@PathVariable (value = "id") Long id){
        return todoListService.findTaskById(id);
    }


    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TodoListModel> getTaskById(@PathVariable (value = "id") Long id, @RequestBody TodoListModel todoListModel){
        return todoListService.updateTaskById(todoListModel, id);
    }


    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTaskById(@PathVariable (value = "id") Long id){
        return todoListService.deleteById(id);
    }

}
