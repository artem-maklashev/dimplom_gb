package org.maklashev.goldengroup.service;

import org.maklashev.goldengroup.model.entity.delays.Delays;
import org.maklashev.goldengroup.model.repositories.delays.DelaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        LocalDateTime start = Utils.convertStringToDate(startDate);
        LocalDateTime end = Utils.convertStringToDate(endDate);
        return delaysRepository.findAllByDelayDateBetween(start.toLocalDate(), end.toLocalDate());}
}
