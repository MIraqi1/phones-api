package com.tmo.phones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phones")
public class PhonesController {

    PhonesService phonesService;

    public PhonesController(PhonesService phonesService) {
        this.phonesService = phonesService;
    }

    @GetMapping()
    public List<Phone> findAllPhones() {
        return phonesService.findAllPhones();
    }

    @GetMapping("/{make}")
    public Phone getPhoneDetails(@PathVariable String make) {
        // get from the service layer (api)
        return phonesService.getPhone(make);
    }

    @PostMapping()
    public ResponseEntity<Phone> addPhone (@Validated @RequestBody Phone phone)
    {
        //code
        phonesService.addPhone(phone);
        return new ResponseEntity<Phone>(phone, HttpStatus.CREATED);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void phoneNotFoundHandler(PhoneNotFoundException phoneNotFoundException) {

    }
}
