package org.t2404e.kanji_together_db.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.t2404e.kanji_together_db.enums.Provider;
import org.t2404e.kanji_together_db.enums.Status;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public Users user;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    public SubscriptionPlans plan;

    @Enumerated(EnumType.STRING)
    public Provider provider;

    public String provider_txn_id;

    public BigDecimal amount;

    @Column(length = 3)
    public String currency;

    @Enumerated(EnumType.STRING)
    public Status status;

    @CreationTimestamp
    @Column(updatable = false)
    public LocalDateTime created_at;

    public LocalDateTime paid_at;
}
