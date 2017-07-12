package reservations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class ReservationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ReservationRepository rr) {
        return args -> {
            Stream.of("Josh", "Jane", "George").forEach(x -> rr.save(new Reservation(x)));
            rr.findAll().forEach(x -> log.info(x.toString()));
        };
    }
}


@RestController
class ReservationRestController {

    private final ReservationRepository rr;

    ReservationRestController(ReservationRepository rr) {
        this.rr = rr;
    }

    @GetMapping(value = "/reservations", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Collection<Reservation> reservations() {
        return this.rr.findAll();
    }
}

interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private String reservationName;

    public Reservation(String reservationName) {
        this.reservationName = reservationName;
    }
}