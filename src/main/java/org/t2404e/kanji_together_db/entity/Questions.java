package org.t2404e.kanji_together_db.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.t2404e.kanji_together_db.enums.QuestionType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "questions")
@Data
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    public Exams exam;

    public String name;
    public String question;

    @Enumerated(EnumType.STRING)
    public QuestionType question_type;

    public Integer kanji_related_id;

    @OneToMany(mappedBy = "question")
    public List<QuestionAnswers> answers;

    @ManyToMany(mappedBy = "questions")
    public List<KanjiCharacters> kanji_characters;

    @CreationTimestamp
    public LocalDateTime create_at;

    @UpdateTimestamp
    public LocalDateTime edit_at;

    public Integer create_by;
    public Integer edit_by;
}
