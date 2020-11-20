package com.tmo.phones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhonesRepository extends JpaRepository<Phone, Long> {
    Phone save(Phone phone);
    List<Phone> findAll();
    Optional<Phone> findById(Long aLong);

    void deleteById(Long aLong);
}
