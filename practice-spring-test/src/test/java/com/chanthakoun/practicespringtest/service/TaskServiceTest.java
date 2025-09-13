package com.chanthakoun.practicespringtest.service;

import com.chanthakoun.practicespringtest.domain.Task;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Test
    void createRejectsBlankTitle() {
        TaskRepository repo = mock(TaskRepository.class);
        TaskService svc = new TaskService(repo);

        assertThrows(ValidationException.class, () -> svc.create(" "));
        verify(repo, never()).save(any());
    }

    @Test
    void createTrimsAndSaves() {
        TaskRepository repo = mock(TaskRepository.class);
        when(repo.save(any(Task.class))).thenAnswer(inv -> inv.getArgument(0));
        TaskService svc = new TaskService(repo);

        Task t = svc.create("  hello  ");
        assertEquals("hello", t.getTitle());
        verify(repo).save(any(Task.class));
    }

    @Test
    void toggleFlipsDone() {
        TaskRepository repo = mock(TaskRepository.class);
        Task task = new Task(); task.setTitle("x");
        when(repo.findById(1L)).thenReturn(Optional.of(task));
        when(repo.save(any(Task.class))).thenAnswer(inv -> inv.getArgument(0));

        TaskService svc = new TaskService(repo);
        Task after = svc.toggle(1L);

        assertTrue(after.isDone());
    }

    @Test
    void listDelegatesToRepo() {
        TaskRepository repo = mock(TaskRepository.class);
        when(repo.findAll()).thenReturn(List.of());
        TaskService svc = new TaskService(repo);

        assertNotNull(svc.list());
        verify(repo).findAll();
    }
}