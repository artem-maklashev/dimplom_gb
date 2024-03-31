package org.maklashev.goldengroup.service;

import org.maklashev.goldengroup.model.entity.gypsumboard.Plan;
import org.maklashev.goldengroup.repositories.gypsumboard.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class PlanService {
    private final PlanRepository planRepository;

    @Autowired
    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<Plan> getPlanData() {
       LocalDate date = LocalDate.now();
       LocalDate startDate = LocalDate.of(date.getYear(), date.getMonth(),1);
        YearMonth yearMonth = YearMonth.from(date);
        LocalDate endDate = yearMonth.atEndOfMonth();
        System.out.println(this.planRepository.findPlansByPlanDateBetween(startDate, endDate).isEmpty());
        return this.planRepository.findPlansByPlanDateBetween(startDate, endDate);
    }
}
