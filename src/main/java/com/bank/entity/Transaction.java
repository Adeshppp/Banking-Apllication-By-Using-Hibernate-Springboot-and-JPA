package com.bank.entity;


import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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

    @JsonIncludeProperties("transactions")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name="fromAccount")
    private Long fromAccountID;

    @Column(name="toAccount")
    private Long toAccountID;

    @Column(name="type")
    private String type;

    @Column(name="amount", nullable = false)
    private Long amount;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date createdAt;

}
//add accountId field to join account and transaction tables
