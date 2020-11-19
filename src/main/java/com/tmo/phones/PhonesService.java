package com.tmo.phones;

import org.springframework.stereotype.Service;

@Service
public class PhonesService {

    PhonesRepository phonesRepository;
    public PhonesService(PhonesRepository phonesRepository) {
        this.phonesRepository = phonesRepository;
    }

    public Phone getPhone(String make) {
        Phone phone = phonesRepository.findPhoneByMake(make);
        if(phone == null) {
            throw new PhoneNotFoundException();
        } else {
            return phone;
        }
    }


    public Phone addPhone(Phone phone) {
        phonesRepository.save(phone);
        return phone;
    }
}
