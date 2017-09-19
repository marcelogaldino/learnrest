package com.learnrest.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fernando
 */
@Entity
@Table(name = "persons")
@NamedQueries({
    @NamedQuery(name = "Person.findByName", query = "select p from Person p where p.name = :name")
})
@XmlRootElement
public class Person extends AbstractEntity {

    @Column(length = 255, nullable = false)
    private String name;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birth;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public Person() {
    }

    public Person(String name, Date birth, User user) {
        this.name = name;
        this.birth = birth;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public void updateParameters(Object entity) {
        final Person other = (Person) entity;
        this.name = other.name;
        this.birth = other.birth;
        this.user = other.user;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", birth=" + birth + ", user=" + user + '}';
    }

}
