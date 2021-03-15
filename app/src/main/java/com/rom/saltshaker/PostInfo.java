package com.rom.saltshaker;

public class PostInfo {
    private String title;
    private String contents;
    private String publisher;
    private String id;

    public PostInfo(String title, String contents, String publisher, String id){
        this.title = title;
        this.contents = contents;
        this.publisher = publisher;
        this.id = id;
    }

    public String getTitle() { return this.title; }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() { return this.contents; }
    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getPublisher() { return this.publisher; }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }
}
