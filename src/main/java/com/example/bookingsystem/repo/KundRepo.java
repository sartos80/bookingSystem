package com.example.bookingsystem.repo;

import com.example.bookingsystem.models.Kund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KundRepo  extends JpaRepository<Kund, Long> {
}
