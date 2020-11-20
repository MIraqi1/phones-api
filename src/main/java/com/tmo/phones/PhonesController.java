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
    public ResponseEntity<List<Phone>> findAllPhones() {
        List<Phone> phones= phonesService.findAllPhones();
        if(phones.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(phones);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Phone> getPhoneById(@PathVariable Long id) {
        Phone phone = phonesService.getPhone(id);
        if(phone == null){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(phone);
        }

    }

    @PostMapping()
    public ResponseEntity<Phone> addPhone (@Validated @RequestBody Phone phone)
    {
        //code
        phonesService.addPhone(phone);
        return new ResponseEntity<Phone>(phone, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        phonesService.deletePhone(id);
        return ResponseEntity.ok("Phone was removed successfully!");
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void phoneNotFoundHandler(PhoneNotFoundException phoneNotFoundException) {

    }

}
