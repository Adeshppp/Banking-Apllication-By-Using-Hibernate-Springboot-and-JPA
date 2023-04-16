package com.bank.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transaction")
public class Transaction{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="transaction_id")
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="account_from_id")
//    @Column(name="from_account", nullable = false)
    private Account fromAccountID;

    @ManyToOne
    @JoinColumn(name="account_to_id")
//    @Column(name="to_account", nullable = false)
    private Account toAccountID;

    @Column(name="type")
    private String type;

    @Column(name="amount", nullable = false)
    private Long amount;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date createdAt;

}
