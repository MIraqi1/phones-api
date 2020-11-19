package com.tmo.phones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhonesRepository extends JpaRepository<Phone, Long> {
    Phone findPhoneByMake(String make);

}
