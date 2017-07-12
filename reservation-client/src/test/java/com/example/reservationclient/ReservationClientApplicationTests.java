package com.example.reservationclient;

import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationClientApplicationTests {

    @Autowired
    private ReservationReader reservationReader;

    @Rule
    public StubRunnerRule stubRunnerRule = new StubRunnerRule()
            .downloadStub("com.example", "reservation-service")
            .workOffline(true)
            .withPort(8000);

    @Test
    public void should_load_all_reservations() {
        Collection<Reservation> reservations = this.reservationReader.read();
        BDDAssertions.then(reservations).hasSize(2);
    }

}
