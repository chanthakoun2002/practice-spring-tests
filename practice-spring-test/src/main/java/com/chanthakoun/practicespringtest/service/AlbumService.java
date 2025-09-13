package com.chanthakoun.practicespringtest.service;

import com.chanthakoun.practicespringtest.domain.Album;
import com.chanthakoun.practicespringtest.repo.AlbumRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> getAllAlbums() {return albumRepository.findAll();}

    public Album create(String title, String artist, Integer year, Album.Format format, BigDecimal price, int stock) {
        if (title == null || title.isBlank()) throw new ValidationException("Title required");
        if (artist == null || artist.isBlank()) throw new ValidationException("Artist required");
        if (price == null || price.signum() < 0) throw new ValidationException("Price needs to be greater than 0");
        if (stock < 0) throw new ValidationException("Stock needs to be greater than 0");

        Album a = new Album();
        a.setTitle(title.trim());
        a.setArtist(artist.trim());
        a.setYear(year);
        a.setFormat(format == null ? Album.Format.VINYL : format);
        a.setPrice(price);
        a.setStock(stock);
        return albumRepository.save(a);
    }

    public Album restock(Long id, int qty) {
        if (qty <= 0) throw new ValidationException("quantity must be more than 0");
        Album a = albumRepository.findById(id).orElseThrow();
        a.setStock(a.getStock() + qty);
        return albumRepository.save(a);
    }

    public Album purchase(Long id, int qty) {
        if (qty <= 0) throw new ValidationException("quantity must be more than 0");
        Album a = albumRepository.findById(id).orElseThrow();
        if (a.getStock() < qty) throw new ValidationException("insufficient stock");
        a.setStock(a.getStock() - qty);
        return albumRepository.save(a);
    }
}
