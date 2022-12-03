package com.ereed.webservice.services;

import com.ereed.webservice.models.Date;
import com.ereed.webservice.repositories.DateRepository;
import com.ereed.webservice.utils.DateDuplicateException;
import com.ereed.webservice.utils.DateInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DateService {

    private final DateRepository dateRepository;

    @Autowired
    public DateService(DateRepository dateRepository) {
        this.dateRepository = dateRepository;
    }

    @Transactional
    public void save(Date date) throws DateDuplicateException, DateInvalidException {

        if (!date.getDate().matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$"))
            throw new DateInvalidException();

        Optional<Date> foundDate = dateRepository.findByDate(date.getDate());
        if (foundDate.isPresent())
            throw new DateDuplicateException();
        dateRepository.save(date);
    }
}
