package com.chanthakoun.practicespringtest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.text.Format;

public class Album {
    // id
    // title
    // artist
    // year published
    // format (vinyl 33 47 78, cd, cassette)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String artist;

    @Min(1880) @Max(2100)
    private Integer year;

    //default to vinyl when non found
    @Enumerated(EnumType.STRING)
    private Format format = Format.VINYL;

    @Min(0)
    private int stock = 0;

    @DecimalMin("0.00") @Digits(integer = 8, fraction = 2)
    private BigDecimal price = BigDecimal.ZERO;

    public enum Format {VINYL, CD, CASSETTE}

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
