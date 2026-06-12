package com.application.phonelog.controller;


import com.application.phonelog.model.PhoneLog;
import com.application.phonelog.services.PhoneLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/phonelog")
public class PhoneLogController {

    private final PhoneLogService phoneLogService;

    public PhoneLogController(PhoneLogService phoneLogService) {
        this.phoneLogService = phoneLogService;
    }


    @GetMapping
    public String getPhoneLogs(Model model){
        List<PhoneLog> phonelogs = phoneLogService.getAllPhoneLogs();
        model.addAttribute("phonelogs", phonelogs);
        return "phonelogs";
    }

    @GetMapping(value = "/{id}")
    public String getPhoneLogById(Model model, @PathVariable Long id){
        PhoneLog phonelog = phoneLogService.getPhoneLogById(id);
        model.addAttribute("phonelogdetailed", phonelog);
        return "phonelogdetailed";
    }

    @GetMapping("newlog")
    public String showAddForm(){
        return "addphonelog.html";
    }

    @PostMapping
    public String addPhoneLog(@RequestParam LocalDate callDate, @RequestParam LocalTime callTime, @RequestParam String phoneNumber, @RequestParam String companyName,
                              @RequestParam String callerName, @RequestParam String recipient, @RequestParam String description, @RequestParam String receiver){
        PhoneLog phoneLog = new PhoneLog(null, callDate, callTime, phoneNumber, companyName, callerName, recipient, description, receiver, Instant.now());

        phoneLogService.addPhoneLog(phoneLog);
        return "redirect:/phonelog";
    }
}
