package org.t2404e.kanji_together_db.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    @Column(unique = true)
    public String email;
    public Boolean has_entrance_exam;
    public Boolean is_active;
    public Boolean is_verified;

    @UpdateTimestamp
    public LocalDateTime edit_at;

    @CreationTimestamp
    @Column(updatable = false)
    public LocalDateTime create_by;

    @UpdateTimestamp
    public LocalDateTime edit_by;

    @OneToMany(mappedBy = "user")
    public List<UserSubscriptions> user_subscriptions;

    @OneToMany(mappedBy = "user")
    public List<Transactions> transactions;

    @ManyToMany(mappedBy = "users")
    public List<Clazz> classes;

    @ManyToMany(mappedBy = "users")
    public List<Categories> categories;

    @OneToMany(mappedBy = "user")
    public List<ExamResults> exam_results;
}
