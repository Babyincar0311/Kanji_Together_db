package org.t2404e.kanji_together_db.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "categories")
@Data
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @CreationTimestamp
    public LocalDateTime create_at;

    @UpdateTimestamp
    public LocalDateTime edit_at;

    public Integer create_by;
    public Integer edit_by;

    // Quan hệ N-N với Users
    @ManyToMany
    @JoinTable(
            name = "categories_rel_users",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public List<Users> users;

    // Quan hệ N-N với Stories
    @ManyToMany
    @JoinTable(
            name = "categories_rel_kanji_stories",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "kanji_story_id")
    )
    public List<KanjiStories> stories;
}
