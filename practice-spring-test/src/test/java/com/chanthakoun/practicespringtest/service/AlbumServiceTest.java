package com.chanthakoun.practicespringtest.service;


import com.chanthakoun.practicespringtest.domain.Album;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlbumServiceTest {

    @Test
    void createRejectsInvalidInput() {
        AlbumRepository repo = mock(AlbumRepository.class);
        AlbumService svc = new AlbumService(repo);
        assertThrows(ValidationException.class, () -> svc.create("","A",2000, Album.Format.VINYL, BigDecimal.TEN, 0));
        assertThrows(ValidationException.class, () -> svc.create("T","",2000, Album.Format.VINYL, BigDecimal.TEN, 0));
        assertThrows(ValidationException.class, () -> svc.create("T","A",2000, Album.Format.VINYL, new BigDecimal("-1"), 0));
        assertThrows(ValidationException.class, () -> svc.create("T","A",2000, Album.Format.VINYL, BigDecimal.TEN, -1));
        verify(repo, never()).save(any());
    }

    @Test
    void purchaseAndRestockAdjustStock() {
        AlbumRepository repo = mock(AlbumRepository.class);
        Album a = new Album(); a.setTitle("T"); a.setArtist("A"); a.setPrice(BigDecimal.TEN); a.setStock(2);
        when(repo.findById(1L)).thenReturn(Optional.of(a));
        when(repo.save(any(Album.class))).thenAnswer(inv -> inv.getArgument(0));

        AlbumService svc = new AlbumService(repo);
        assertThrows(ValidationException.class, () -> svc.purchase(1L, 3));

        Album afterRestock = svc.restock(1L, 5);
        assertEquals(7, afterRestock.getStock());

        Album afterPurchase = svc.purchase(1L, 2);
        assertEquals(5, afterPurchase.getStock());
        verify(repo, atLeastOnce()).save(any());
    }
}