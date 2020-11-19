package com.tmo.phones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PhonesController {

    PhonesService phonesService;

    public PhonesController(PhonesService phonesService) {
        this.phonesService = phonesService;
    }

    @GetMapping("/phones/{make}")
    public Phone getPhoneDetails(@PathVariable String make) {
        // get from the service layer (api)
        return phonesService.getPhone(make);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void phoneNotFoundHandler(PhoneNotFoundException phoneNotFoundException) {

    }
}
