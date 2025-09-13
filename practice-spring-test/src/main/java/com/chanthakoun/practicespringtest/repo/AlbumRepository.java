package com.chanthakoun.practicespringtest.repo;

import com.chanthakoun.practicespringtest.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByArtistContainingIgnoreCase(String artist);
    List<Album> findByTitleContainingIgnoreCase(String title);
}