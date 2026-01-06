package org.t2404e.kanji_together_db.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "kanji_characters")
@Data
public class KanjiCharacters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 10)
    public String kanji;

    @Column(length = 10)
    public String on_pronuncication;

    @Column(length = 10)
    public String kun_pronuncication;

    public Integer num_strokes;
    public Integer JLPT;
    public String kanji_description;

    @CreationTimestamp
    @Column(updatable = false)
    public LocalDateTime create_at;

    @UpdateTimestamp
    public LocalDateTime edit_at;

    public Integer create_by;
    public Integer edit_by;

    // Quan hệ N-N với Lesson
    @ManyToMany
    @JoinTable(
            name = "kanji_characters_rel_lesson",
            joinColumns = @JoinColumn(name = "kanji_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    public List<KanjiLessons> lessons;

    // Quan hệ N-N với Question
    @ManyToMany
    @JoinTable(
            name = "kanji_characters_rel_question",
            joinColumns = @JoinColumn(name = "kanji_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    public List<Questions> questions;
}
