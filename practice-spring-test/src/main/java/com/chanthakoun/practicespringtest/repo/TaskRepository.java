package com.chanthakoun.practicespringtest.repo;


import com.chanthakoun.practicespringtest.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {}