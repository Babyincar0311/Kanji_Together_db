package org.t2404e.kanji_together_db.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "exam_results")
@Data
public class ExamResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public Users user;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    public Exams exam;

    public Integer total_question;
    public Integer correct_answer;

    @CreationTimestamp
    public LocalDateTime create_at;
}