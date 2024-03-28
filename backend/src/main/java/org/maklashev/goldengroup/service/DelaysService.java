package org.maklashev.goldengroup.service;

import org.maklashev.goldengroup.model.entity.delays.Delays;
import org.maklashev.goldengroup.repositories.delays.DelaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DelaysService {
    private final DelaysRepository delaysRepository;

    @Autowired
    public DelaysService(
            DelaysRepository delaysRepository) {
        this.delaysRepository = delaysRepository;
    }

    public List<Delays> getDelaysByDate(String startDate, String endDate) {
        LocalDate start = Utils.convertStringToDate(startDate).toLocalDate();
        LocalDate end = Utils.convertStringToDate(endDate).toLocalDate();
        System.out.println(delaysRepository.findAllByDelayDateBetween(start, end).size());
        System.out.println(delaysRepository.findIdsInDateRange(start, end).size());
        return delaysRepository.findAllByDelayDateBetween(start,end);}
}
