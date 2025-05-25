package com.example.bookingsystem.controllers;
import com.example.bookingsystem.services.BokningService;
import com.example.bookingsystem.services.KundService;
import com.example.bookingsystem.services.RumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path="/kunder")
public class RumController {

    private final KundService kundService;
    private final BokningService bokningService;
    private final RumService rumService;


}

