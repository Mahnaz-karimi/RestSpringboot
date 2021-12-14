package com.restServicesSpring.restService.Booking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class BookingControllerTest {

    @Autowired
    RestServiceApplication restServiceApplication;


    @Test
    void bookings()  throws Exception{
        RestServiceApplication restServiceApplication = new RestServiceApplication();
        assertThat(restServiceApplication).isNotNull();
        System.out.println("helllllllo"+ restServiceApplication.toString());
    }
}

