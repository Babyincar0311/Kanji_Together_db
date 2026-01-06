package org.t2404e.kanji_together_db.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "kanji_stories")
@Data
public class KanjiStories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Integer kanji_int;
    public String kanji_stories;

    @CreationTimestamp
    public LocalDateTime create_at;

    @UpdateTimestamp
    public LocalDateTime edit_at;

    public Integer create_by;
    public Integer edit_by;

    // Quan hệ N-N với Categories
    @ManyToMany(mappedBy = "stories")
    public List<Categories> categories;
}
