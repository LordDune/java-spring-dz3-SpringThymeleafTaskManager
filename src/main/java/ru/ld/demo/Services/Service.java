package ru.ld.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ld.demo.Models.Task;
import ru.ld.demo.Repository.Repository;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository repository;

    public List<Task> getAll(){
        return repository.findAll();
    }

    public Task save(Task task){
        repository.save(task);
        return task;
    }

    public void complete(int id) {
        repository.complete(id);
    }
}
