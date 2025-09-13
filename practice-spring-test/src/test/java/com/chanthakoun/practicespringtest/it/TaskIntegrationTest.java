package com.chanthakoun.practicespringtest.it;


import com.chanthakoun.practicespringtest.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaskIntegrationTest {

    @Autowired
    TaskRepository repo;

    @Test
    void createAndFind() {
        Task t = new Task();
        t.setTitle("Integration test");
        repo.save(t);

        assertThat(repo.findAll()).extracting(Task::getTitle)
                .contains("Integration test");
    }
}