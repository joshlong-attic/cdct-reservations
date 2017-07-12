package reservations;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = ReservationServiceApplication.class)
@RunWith(SpringRunner.class)
public class BaseClass {

    @MockBean
    ReservationRepository reservationRepository;

    @Autowired
    private ReservationRestController controller ;

    @Before
    public void before() {
        RestAssuredMockMvc.standaloneSetup(this.controller);

        when(reservationRepository.findAll()).thenReturn(Arrays.asList(
                new Reservation(1L, "Josh"), new Reservation(2L, "Jane")));
    }
}
