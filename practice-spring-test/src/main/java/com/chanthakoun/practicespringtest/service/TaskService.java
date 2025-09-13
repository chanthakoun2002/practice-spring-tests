package com.chanthakoun.practicespringtest.service;

import com.chanthakoun.practicespringtest.domain.Task;
import com.chanthakoun.practicespringtest.repo.TaskRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> list() {
        return repo.findAll();
    }

    public Task create(String title) {
        if (title == null || title.isBlank()) {
            throw new ValidationException("Title required");
        }
        Task t = new Task();
        t.setTitle(title.trim());
        return repo.save(t);
    }

    public Task toggle(Long id) {
        Task t = repo.findById(id).orElseThrow();
        t.setDone(!t.isDone());
        return repo.save(t);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}