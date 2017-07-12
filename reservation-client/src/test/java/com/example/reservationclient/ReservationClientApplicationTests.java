package com.example.reservationclient;

import java.util.Collection;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = "com.example:reservation-service:+:stubs:8000",
        workOffline = true)
public class ReservationClientApplicationTests {

    @Autowired
    private ReservationReader reservationReader;

    @Test
    public void should_load_all_reservations() {
        Collection<Reservation> reservations = this.reservationReader.read();
        BDDAssertions.then(reservations).hasSize(2);
    }

}
