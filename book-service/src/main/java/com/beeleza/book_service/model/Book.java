package com.beeleza.book_service.model;

import java.util.Date;
import java.util.Objects;

public class Book {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String author;
    private String title;
    private Date laucheDate;
    private Double price;
    private String currency;
    private String environment;

    public Book() {
    }

    public Book(Long id, String author, String title, Date laucheDate, Double price, String currency, String environment) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.laucheDate = laucheDate;
        this.price = price;
        this.currency = currency;
        this.environment = environment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLaucheDate() {
        return laucheDate;
    }

    public void setLaucheDate(Date laucheDate) {
        this.laucheDate = laucheDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(author, book.author) && Objects.equals(title, book.title) && Objects.equals(laucheDate, book.laucheDate) && Objects.equals(price, book.price) && Objects.equals(currency, book.currency) && Objects.equals(environment, book.environment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, laucheDate, price, currency, environment);
    }
}
