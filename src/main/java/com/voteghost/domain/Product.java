package com.voteghost.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "product")
    private Set<Feature> features = new HashSet();

    private boolean published;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", features=" + features +
                ", published=" + published +
                '}';
    }
}
