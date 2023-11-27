package org.maklashev.goldengroup.model.entity.delays;

import jakarta.persistence.*;
import lombok.Data;
import org.maklashev.goldengroup.model.entity.Shift;
import org.maklashev.goldengroup.model.entity.gypsumboard.GypsumBoard;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "delays")
public class Delays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int  id;

    @Column(name = "delay_date")
    private LocalDate delayDate;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "unit_part_id")
    private UnitPart unitPart;

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;

    @ManyToOne
    @JoinColumn(name = "gypsum_board_id")
    private GypsumBoard gypsumBoard;

    @ManyToOne
    @JoinColumn(name = "delay_type_id")
    private DelayType delayType;
}
