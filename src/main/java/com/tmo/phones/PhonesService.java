package com.tmo.phones;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhonesService {

    PhonesRepository phonesRepository;
    public PhonesService(PhonesRepository phonesRepository) {
        this.phonesRepository = phonesRepository;
    }

    public Phone addPhone(Phone phone) {
        phonesRepository.save(phone);
        return phone;
    }

    public List<Phone> findAllPhones() {
        List<Phone> phones =  phonesRepository.findAll();
        if(phones.isEmpty()) {
            throw new PhoneNotFoundException();
        } else {
            return phones;
        }
    }

    public void deletePhone(Long id) {
        phonesRepository.deleteById(id);

    }

    public Phone getPhone(Long id) {
        Optional<Phone> phone = phonesRepository.findById(id);
        return phone.orElse(null);
    }
}
