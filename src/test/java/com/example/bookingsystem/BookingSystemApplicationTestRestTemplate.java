package com.example.bookingsystem;

import com.example.bookingsystem.models.Kund;
import com.example.bookingsystem.repo.BokningRepo;
import com.example.bookingsystem.repo.KundRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingSystemApplicationTestRestTemplate
{
    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private KundRepo kundRepo;

    @Autowired
    private BokningRepo bokningRepo;

    Kund kund1 = new Kund(null, "Ture", "ture@mail", "589405830", List.of());
    Kund kund2 = new Kund(null, "Saga", "saga@mail", "987605830", List.of());


    @BeforeEach
    public void setUp()
    {
        bokningRepo.deleteAll();
        kundRepo.deleteAll();
        kundRepo.save(kund1);
        kundRepo.save(kund2);
    }

    @Test
    void contextLoads() {
        assertThat(restTemplate).isNotNull();
    }

    @Test
    public void getKundFormTest(){
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/kunder/addKund", String.class)).contains("Kunder");
    //ev lägga till fler contains när html är uppdaterad
    }

    @Test
    public void getAllKunderTest(){
        ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/kunder/kunderAll", String.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getContentType().toString()).contains("text/html");
        assertThat(response.getBody()).contains("/kunder/updateKund/");
        assertThat(response.getBody()).contains("/kunder/addKund");
        assertThat(response.getBody()).contains(kund1.getName());
        assertThat(response.getBody()).contains(kund2.getName());
        assertThat(response.getBody()).contains("Update Info");
        assertThat(response.getBody()).contains("Add Bokning");
    }

    @Test
    public void updateKundTest()
    {
        Long id = kund1.getId();

        ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/kunder/updateKund/"+id, String.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getContentType().toString()).contains("text/html");
        assertThat(response.getBody()).contains(kund1.getName());
        assertThat(response.getBody()).doesNotContain(kund2.getEpost());
    }

}
