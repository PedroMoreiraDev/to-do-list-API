package com.example.todolist.service;

import com.example.todolist.model.TodoListModel;
import com.example.todolist.repository.TodoListRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoListService {

    private TodoListRepository todoListRepository;

    public TodoListModel createTask (TodoListModel task){
    return todoListRepository.save(task);
    }

    public List<TodoListModel> listAllTasks(){
        return todoListRepository.findAll();
    }


    public ResponseEntity<TodoListModel> findTaskById(Long id){
        return todoListRepository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<TodoListModel> updateTaskById(TodoListModel todoListModel, Long id){
        return todoListRepository.findById(id)
                .map(taskToUpdate ->{
                    taskToUpdate.setTitle(todoListModel.getTitle());
                    taskToUpdate.setDescription(todoListModel.getDescription());
                    taskToUpdate.setDeadLine(todoListModel.getDeadLine());
                    TodoListModel updated = todoListRepository.save(taskToUpdate);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteById(Long id){
        return todoListRepository.findById(id)
                .map(taskToDelete -> {
                    todoListRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
