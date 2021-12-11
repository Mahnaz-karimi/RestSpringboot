package com.restServicesSpring.restService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookingRestControllerTest {
    @Autowired
    private BookingRestController bookingRestController;

    @Test
    void bookings()  throws Exception{
        assertThat(bookingRestController).isNull();
    }
}