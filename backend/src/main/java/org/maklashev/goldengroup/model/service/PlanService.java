package org.maklashev.goldengroup.model.service;

import org.maklashev.goldengroup.model.entity.gypsumboard.Plan;
import org.maklashev.goldengroup.model.repositories.gypsumboard.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        short month = (short) date.getMonth().getValue();
        int year = date.getYear();
        return this.planRepository.findAllByPlanDateMonthAndPlanDateYear(month, year);
    }
}
