package com.chanthakoun.practicespringtest.controller;

import com.chanthakoun.practicespringtest.domain.Album;
import com.chanthakoun.practicespringtest.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumService service;
    public AlbumController(AlbumService service) {this.service = service;}

    @GetMapping
    public List<Album> list() { return service.getAllAlbums(); }

    @PostMapping
    public Album create(@RequestParam String title,
                        @RequestParam String artist,
                        @RequestParam(required = false) Integer year,
                        @RequestParam(required = false) Album.Format format,
                        @RequestParam BigDecimal price,
                        @RequestParam(defaultValue = "0") int stock) {
        return service.create(title, artist, year, format, price, stock);
    }

    @PostMapping("/{id}/restock")
    public Album restock(@PathVariable Long id, @RequestParam int qty) {
        return service.restock(id, qty);
    }

    @PostMapping("/{id}/purchase")
    public Album purchase(@PathVariable Long id, @RequestParam int qty) {
        return service.purchase(id, qty);
    }
}