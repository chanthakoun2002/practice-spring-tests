package com.chanthakoun.practicespringtest.controller;

import com.chanthakoun.practicespringtest.service.AlbumService;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AlbumControllerTest {

    @Test
    void getListReturns200() throws Exception {
        AlbumService svc = mock(AlbumService.class);
        when(svc.getAllAlbums()).thenReturn(List.of());
        MockMvc mvc = MockMvcBuilders.standaloneSetup(new AlbumController(svc)).build();

        mvc.perform(get("/api/albums"))
                .andExpect(status().isOk());
    }

    @Test
    void createCallsServiceAndReturns200() throws Exception {
        AlbumService svc = mock(AlbumService.class);
        MockMvc mvc = MockMvcBuilders.standaloneSetup(new AlbumController(svc)).build();

        mvc.perform(post("/api/albums")
                        .param("title","Blue Train")
                        .param("artist","John Coltrane")
                        .param("year","1957")
                        .param("price","17.99")
                        .param("stock","3"))
                .andExpect(status().isOk());

        verify(svc).create(eq("Blue Train"), eq("John Coltrane"), eq(1957),
                any(), eq(new BigDecimal("17.99")), eq(3));
    }
}
