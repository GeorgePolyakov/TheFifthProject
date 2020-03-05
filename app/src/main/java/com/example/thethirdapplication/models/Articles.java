package com.example.thethirdapplication.models;

public class Articles {

    private Source source;
    private String author;
    private String title;
    private String publishedAt;
    private String description;
    private String urlToImage;

    public void setDescription(String description) {this.description = description; }

    public void setPublishedAt(String publishedAt) {this.publishedAt = publishedAt;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlToImage() { return urlToImage; }

    public Source getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

}
