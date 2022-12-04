package com.ereed.webservice.repositories;

import com.ereed.webservice.models.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface DateRepository extends JpaRepository<Date, Integer> {
    Optional<Date> findByDate(String strDate);
}
