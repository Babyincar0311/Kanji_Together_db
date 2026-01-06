package org.t2404e.kanji_together_db.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.t2404e.kanji_together_db.enums.Status;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_subscriptions")
@Data
public class UserSubscriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public Users user;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    public SubscriptionPlans plan;

    public LocalDateTime start_at;
    public LocalDateTime end_at;

    @Enumerated(EnumType.STRING)
    public Status status;

    @CreationTimestamp
    @Column(updatable = false)
    public LocalDateTime created_at;
}
