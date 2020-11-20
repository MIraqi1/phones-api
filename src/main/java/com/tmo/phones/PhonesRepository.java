package com.tmo.phones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhonesRepository extends JpaRepository<Phone, Long> {
    Phone findPhoneByMake(String make);
    Phone save(Phone phone);
    List<Phone> findAll();
}
