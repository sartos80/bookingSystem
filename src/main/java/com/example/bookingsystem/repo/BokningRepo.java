package com.example.bookingsystem.repo;

import com.example.bookingsystem.models.Bokning;
import com.example.bookingsystem.models.Rum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BokningRepo extends JpaRepository<Bokning, Long>
{

}
