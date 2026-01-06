package org.t2404e.kanji_together_db.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "question_answers")
@Data
public class QuestionAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    public Questions question;

    public Boolean is_correct_answer;

    @Column(columnDefinition = "TEXT")
    public String answer;

    @CreationTimestamp
    public LocalDateTime create_at;

    @UpdateTimestamp
    public LocalDateTime edit_at;

    public Integer create_by;
    public Integer edit_by;
}
