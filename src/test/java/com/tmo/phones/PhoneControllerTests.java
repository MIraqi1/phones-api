package com.tmo.phones;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhonesController.class)
public class PhoneControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PhonesService phonesService;

    @Test
    void getPhone_phoneExists_returnPhoneDetails() throws Exception {

        when(phonesService.getPhone(anyString())).thenReturn(new Phone("iphone", "11 max", "ios"));
        mockMvc.perform(get("/phones/iphone"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("make").value("iphone"))
                .andExpect(jsonPath("model").value("11 max"));
    }

    @Test
    void getCar_notExists_returnsNoContent() throws Exception {
        when(phonesService.getPhone(anyString())).thenThrow(new PhoneNotFoundException());

        mockMvc.perform(get("/phones/foo"))
                .andExpect(status().isNoContent());
    }
}
