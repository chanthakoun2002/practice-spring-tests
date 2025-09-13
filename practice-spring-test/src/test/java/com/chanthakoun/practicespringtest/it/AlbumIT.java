package com.chanthakoun.practicespringtest.it;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AlbumIT {

    @Autowired MockMvc mvc;

    @Test
    void createThenList() throws Exception {
        mvc.perform(post("/api/albums")
                        .param("title","Test LP").param("artist","Test Band")
                        .param("price","9.99").param("stock","1"))
                .andExpect(status().isOk());

        mvc.perform(get("/api/albums"))
                .andExpect(status().isOk());
    }
}
