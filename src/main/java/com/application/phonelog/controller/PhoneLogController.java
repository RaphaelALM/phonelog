package com.application.phonelog.controller;


import com.application.phonelog.model.PhoneLog;
import com.application.phonelog.services.PhoneLogService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    // Get phone logs
    @GetMapping
    public String getPhoneLogs(@RequestParam(required = false) String keyword, Model model){

        model.addAttribute("phonelogs", getPhoneLogs(keyword));
        model.addAttribute("keyword", keyword);
        return "phonelogs";
    }

    // CSV File
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportCsv(@RequestParam(required = false) String keyword) throws IOException {

        List<PhoneLog> logs = getPhoneLogs(keyword);

        StringBuilder csv = new StringBuilder();

        // for Excel to identify UTF-8 correctly
        csv.append("\uFEFF");
        csv.append("ID,日付,時間,会社名,電話番号,名前,誰宛,内容,受けた人,入力日時\n");

        for (PhoneLog log : logs) {
            csv.append(log.getId()).append(",");
            csv.append(log.getCallDate()).append(",");
            csv.append(log.getCallTime()).append(",");
            csv.append(log.getCompanyName()).append(",");
            csv.append(log.getPhoneNumber()).append(",");
            csv.append(log.getCallerName()).append(",");
            csv.append(log.getRecipient()).append(",");
            csv.append(log.getDescription().replaceAll("\\R", " ")).append(",");
            csv.append(log.getReceiver()).append(",");
            csv.append(log.getEntryDate()).append("\n");
        }

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=phonelogs.csv"
                )
                .contentType(MediaType.parseMediaType("text/csv; charset=UTF-8"))
                .body(csv.toString().getBytes(StandardCharsets.UTF_8));
    }


    @GetMapping(value = "/{id}")
    public String getPhoneLogById(Model model, @PathVariable Long id){
        PhoneLog phonelog = phoneLogService.getPhoneLogById(id);
        model.addAttribute("phonelogdetailed", phonelog);
        return "phonelogdetailed";
    }

    @GetMapping("/newlog")
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

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model){
        PhoneLog phoneLog = phoneLogService.getPhoneLogById(id);

        System.out.println("CALLDATE = " + phoneLog.getCallDate());

        model.addAttribute("phoneLog", phoneLog);
        return "edit";
    }

    @PostMapping("/update")
    public String updateLog(@ModelAttribute PhoneLog phoneLog) {

        PhoneLog existing = phoneLogService.getPhoneLogById(phoneLog.getId());

        phoneLog.setEntryDate(existing.getEntryDate());

        phoneLogService.addPhoneLog(phoneLog);

        return "redirect:/phonelog";
    }

    @GetMapping("/{id}/delete")
    public String deleteLog(@PathVariable Long id){
        phoneLogService.deleteLog(id);
        return "redirect:/phonelog";
    }


    // Check keyword and get list function
    private List<PhoneLog> getPhoneLogs(String keyword){
        if (keyword != null && !keyword.isBlank()) {
            return phoneLogService.findByKeyword(keyword);
        }
        return phoneLogService.getAllPhoneLogs();
    }


}
