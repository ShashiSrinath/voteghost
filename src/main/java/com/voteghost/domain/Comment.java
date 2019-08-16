package com.voteghost.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Comment {
    @EmbeddedId
    private CommentID pk;
    @Column(length = 5000)
    private String text;

    public CommentID getPk() {
        return pk;
    }

    public void setPk(CommentID pk) {
        this.pk = pk;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
