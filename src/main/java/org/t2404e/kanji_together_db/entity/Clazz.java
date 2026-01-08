package org.t2404e.kanji_together_db.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "clazz")
@Data
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;

    @CreationTimestamp
    @Column(updatable = false)
    public LocalDateTime create_at;

    @UpdateTimestamp
    public LocalDateTime edit_at;

    public Integer create_by;
    public Integer edit_by;

    @ManyToMany
    @JoinTable(
            name = "clazz_rel_users",
            joinColumns = @JoinColumn(name = "clazz_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public List<Users> users;
}
