package com.tmo.phones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhoneServiceTest {
    @Mock
    private PhonesRepository phonesRepository;

    private PhonesService phonesService;

    @BeforeEach
    void setup() {
        phonesService = new PhonesService(phonesRepository);
    }

    @Test
    void getPhone_exists_returnsDetails() {
        when(phonesRepository.findPhoneByMake(anyString()))
                .thenReturn(new Phone("iphone", "11 max", "ios"));

        Phone phone = phonesService.getPhone("iphone");
        assertThat(phone).isNotNull();
        assertThat(phone.getMake()).isEqualTo("iphone");
        assertThat(phone.getModel()).isEqualTo("11 max");
    }

    @Test
    void getPhone_notExists_throwsError() {
        when(phonesRepository.findPhoneByMake(anyString())).thenReturn(null);
        assertThatThrownBy(
                () -> phonesService.getPhone("foo")
        ).isInstanceOf(PhoneNotFoundException.class);
    }

    @Test
    void canAddPhone() {
        Phone ip12 = new Phone("Apple", "iPhone 12", "ios", LocalDate.of(2020, 11, 15));
        when(phonesRepository.save(ip12)).thenReturn(ip12);

        Phone actual = phonesService.addPhone(ip12);

        assertEquals(ip12, actual);
    }

}