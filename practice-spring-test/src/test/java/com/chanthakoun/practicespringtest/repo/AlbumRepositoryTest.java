package com.chanthakoun.practicespringtest.repo;

import com.chanthakoun.practicespringtest.domain.Album;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AlbumRepositoryTest {

    @Autowired
    AlbumRepository repo;

    @Test
    void saveAndFindersWork() {
        Album a = new Album();
        a.setTitle("Kind of Blue");
        a.setArtist("Miles Davis");
        a.setYear(1959);
        a.setPrice(new BigDecimal("19.99"));
        a.setStock(5);
        repo.save(a);

        assertThat(repo.findByArtistContainingIgnoreCase("miles")).hasSize(1);
        assertThat(repo.findByTitleContainingIgnoreCase("kind")).hasSize(1);
    }
}
