package com.voteghost.domain;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class VoteId implements Serializable {

    private static final long serialVersionUID = -743880302334190547L;
    @ManyToOne
    private User user;

    @ManyToOne
    private Feature feature;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
