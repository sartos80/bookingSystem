package com.example.bookingsystem.repo;

import com.example.bookingsystem.models.Bokning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BokningRepo extends JpaRepository<Bokning, Long>
{
}
