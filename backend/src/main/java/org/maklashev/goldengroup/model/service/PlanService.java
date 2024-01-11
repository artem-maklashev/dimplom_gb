package org.maklashev.goldengroup.model.service;

import org.maklashev.goldengroup.model.entity.gypsumboard.Plan;
import org.maklashev.goldengroup.model.repositories.gypsumboard.PlanRepository;
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
//       LocalDate startDate = LocalDate.of(2023, 12,1);
        YearMonth yearMonth = YearMonth.from(date);
        LocalDate endDate = yearMonth.atEndOfMonth();
//        LocalDate endDate = LocalDate.of(2023, 12, 31);
        System.out.println(this.planRepository.findPlansByPlanDateBetween(startDate, endDate).isEmpty());
        return this.planRepository.findPlansByPlanDateBetween(startDate, endDate);
    }
}
