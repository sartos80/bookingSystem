package com.example.bookingsystem;

import com.example.bookingsystem.dtos.DetaljerKundDto;
import com.example.bookingsystem.dtos.KundDto;
import com.example.bookingsystem.models.Kund;
import com.example.bookingsystem.repo.BokningRepo;
import com.example.bookingsystem.repo.KundRepo;
import com.example.bookingsystem.services.impl.KundServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class BookingSystemApplicationServiceTest
{
    @Autowired
    KundRepo kundRepo;

    @Autowired
    BokningRepo bokningRepo;

    @Autowired
    KundServiceImpl kundService;

    String name1 = "Janne";
    String name2 = "Stina";
    String email1 = "janne@mail";
    String email2 = "stina@mail";
    String phone1 = "48332920";
    String phone2 = "58343265";

    Kund kund1 = new Kund(null, name1, email1, phone1, List.of());
    Kund kund2 = new Kund(null, name2, email2, phone2, List.of());

    DetaljerKundDto detaljerKundDto1 = new DetaljerKundDto(null, "Hans", "hans@mail", "0728337645", List.of());
    DetaljerKundDto detaljerKundDto2 = DetaljerKundDto.builder().id(null).name(name2).epost(email2).telefonnummer(phone2).bokningar(List.of()).build();

    @BeforeEach
    public void setUp()
    {
        bokningRepo.deleteAll();
        kundRepo.deleteAll();
        kundRepo.save(kund1);
        kundRepo.save(kund2);
    }

    @Test
    public void getAllKunderTest(){
        List<DetaljerKundDto> allaKunder = kundService.getAllKunder();

        assertEquals(allaKunder.size(), 2);
        assertTrue(allaKunder.stream().map(k -> k.getName()).toList().contains("Janne"));
        assertTrue(allaKunder.stream().map(k -> k.getEpost()).toList().contains("stina@mail"));
        assertFalse(allaKunder.stream().map(k -> k.getTelefonnummer()).toList().contains("0"));
    }

    @Test
    public void addKund(){
        DetaljerKundDto nyKund = new DetaljerKundDto(null, "Pelle", "pelle@mail.com", "0701234567", List.of());
        assertEquals(kundService.getAllKunder().size(), 2);
        kundService.addKund(nyKund);
        List<DetaljerKundDto> kunder = kundService.getAllKunder();

        assertEquals(3, kunder.size());
        assertTrue(kunder.stream().map(k -> k.getName()).toList().contains("Pelle"));
        assertTrue(kunder.stream().map(k -> k.getEpost()).toList().contains("pelle@mail.com"));
    }

    @Test
    public void getKundByIdTest(){
        DetaljerKundDto kund = kundService.getKundById(kund1.getId());
        assertEquals(kund.getName(), kund1.getName());
        assertEquals(kund.getEpost(), kund1.getEpost());
        assertEquals(kund.getTelefonnummer(), kund1.getTelefonnummer());
    }

    @Test
    public void deleteKundByIdTest()
    {
        kundService.deleteKund(kund1.getId());
        List<DetaljerKundDto> kunder = kundService.getAllKunder();
        assertFalse(kunder.stream().map(k -> k.getId()).toList().contains(kund1.getId()));
        assertTrue(kunder.stream().map(k -> k.getName()).toList().contains(kund2.getName()));
    }


    @Test
    public void kundToKundDtoTest(){
        KundDto kundDto = kundService.kundToKundDto(kund1);
        assertEquals(kundDto.getName(), kund1.getName());
        assertEquals(kundDto.getId(), kund1.getId());
    }

    @Test
    public void kundToDetaljerKundDtoTest()
    {
        DetaljerKundDto detaljerKundDto = kundService.kundToDetaljerKundDto(kund2);

        assertEquals(detaljerKundDto.getId(), kund2.getId());
        assertEquals(detaljerKundDto.getName(), detaljerKundDto2.getName());
        assertEquals(detaljerKundDto.getEpost(), detaljerKundDto2.getEpost());
        assertEquals(detaljerKundDto.getTelefonnummer(), detaljerKundDto2.getTelefonnummer());
    }

    @Test
    public void kundDtoToKundTest()
    {
        Kund kund = kundService.DetaljerKundDtoToKund(detaljerKundDto1);
        assertEquals(kund.getName(), detaljerKundDto1.getName());
        assertEquals(kund.getEpost(), detaljerKundDto1.getEpost());
        assertEquals(kund.getTelefonnummer(), detaljerKundDto1.getTelefonnummer());
    }
}
