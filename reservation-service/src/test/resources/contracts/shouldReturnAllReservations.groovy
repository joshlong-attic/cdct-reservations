import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    request {
        method GET()
        url("/reservations")
    }
    response {
        status 200
        body([[id: 1, reservationName: "Josh"], [id: 2, reservationName: "Jane"]])
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
        }
    }
}