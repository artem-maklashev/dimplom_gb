package org.maklashev.goldengroup.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "types_id")
    private Types pType;

    @ManyToOne
    @JoinColumn(name = "trade_mark_id")
    private TradeMark tradeMark;
}

