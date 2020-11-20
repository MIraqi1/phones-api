package com.tmo.phones;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhonesController.class)
public class PhoneControllerTests {

    ObjectMapper mapper = new ObjectMapper();
    {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    private static final String apiUrl = "/api/phones";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PhonesService phonesService;

    @Test
    void getPhone_phoneExists_returnPhoneDetails() throws Exception {

        when(phonesService.getPhone(anyString())).thenReturn(new Phone("iphone", "11 max", "ios"));
        mockMvc.perform(get(apiUrl + "/iphone"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("make").value("iphone"))
                .andExpect(jsonPath("model").value("11 max"));
    }

    @Test
    void getPhone_notExists_returnsNoContent() throws Exception {
        when(phonesService.getPhone(anyString())).thenThrow(new PhoneNotFoundException());

        mockMvc.perform(get(apiUrl + "/foo"))
                .andExpect(status().isNoContent());
    }

    @Test
    void addNewPhone_returnPhone() throws Exception{
        Phone phone = new Phone("iphone", "11 max", "ios", LocalDate.now());
        when(phonesService.addPhone(any(Phone.class))).thenReturn(phone);
        mockMvc.perform(post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(phone)))
                .andExpect(status().isCreated());

    }

    @Test
    void getPhones_returnsListOfPhones() throws Exception{
        List<Phone> phones = new ArrayList<>();
        phones.add(new Phone("iphone", "12", "ios", LocalDate.now()));
        phones.add(new Phone("iphone", "se", "ios", LocalDate.now()));
        phones.add(new Phone("galaxy", "8", "android", LocalDate.now()));
        phones.add(new Phone("galaxy", "11", "android", LocalDate.now()));
        phones.add(new Phone("pixel", "5", "android", LocalDate.now()));

        when(phonesService.findAllPhones()).thenReturn(phones);

        mockMvc.perform(get(apiUrl))
                .andExpect(status().isOk());
    }
}
