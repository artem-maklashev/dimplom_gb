package org.maklashev.goldengroup.model.entity.production;

import jakarta.persistence.*;
import lombok.Data;
import org.maklashev.goldengroup.model.entity.Shift;
import org.maklashev.goldengroup.model.entity.Types;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "productionlog")
public class ProductionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "production_start")
    private LocalDateTime productionStart;

    @Column(name = "production_finish")
    private LocalDateTime productionFinish;

    @Column(name = "production_date")
    private LocalDate productionDate;

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;

    @ManyToOne
    @JoinColumn(name = "product_types_id")
    private Types type;

}
