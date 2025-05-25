package com.example.bookingsystem;

import com.example.bookingsystem.dtos.DetaljerKundDto;
import com.example.bookingsystem.models.Kund;
import com.example.bookingsystem.repo.BokningRepo;
import com.example.bookingsystem.repo.KundRepo;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Rollback
public class BookingSystemApplicationControllerTest
{
    @Test
    void contextLoads()
    {
    }

    @Autowired
    private MockMvc mockMvc;

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
    public void returntest() throws Exception
    {
        mockMvc.perform(get("/kunder/kunderAll")).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getAllKunder() throws Exception
    {
       MvcResult result = mockMvc.perform(get("/kunder/kunderAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("kunderAll"))
               .andExpect(model().attributeExists("allKunder"))
               .andExpect(model().attribute("allKunder", Matchers.hasSize(2)))
               .andExpect(model().attributeExists("name"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void getKundFormTest() throws Exception
    {
        MvcResult result = mockMvc.perform(get("/kunder/addKund"))
                .andExpect(status().isOk())
                .andExpect(view().name("kunderForm"))
                .andExpect(model().attribute("kund", Matchers.instanceOf(DetaljerKundDto.class)))
                .andExpect(model().attributeExists("kund"))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        System.out.println(response);
    }

    @Test
    public void postKundTest() throws Exception
    {
        String json = """
                "name":"Hans,
                "epost":"hans@mail.com",
                "telefonnummer":"0731234567"
                """;

        mockMvc.perform(post("/kunder/postKund")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().is3xxRedirection())
        .andReturn();

    }
}
