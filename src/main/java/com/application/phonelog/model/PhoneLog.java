package com.application.phonelog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "test_log")
public class PhoneLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "telp_date")
    private LocalDate callDate;

    @NotNull
    @Column(name = "telp_time")
    private LocalTime callTime;

    @NotBlank
    @Column(name = "telp_no")
    private String phoneNumber;

    @NotBlank
    @Column(name = "company")
    private String companyName;

    @NotBlank
    @Column(name = "caller")
    private String callerName;

    @NotBlank
    @Column(name = "callee")
    private String recipient;

    @NotBlank
    @Column(name = "reason")
    private String description;

    @NotBlank
    @Column(name = "taken_by")
    private String receiver;

    @NotNull
    @Column(name = "created_date")
    private Instant entryDate;

    public PhoneLog(){
    }

    public PhoneLog(Long id, LocalDate callDate, LocalTime callTime, String phoneNumber, String companyName, String callerName, String recipient, String description, String receiver, Instant entryDate) {
        this.id = id;
        this.callDate = callDate;
        this.callTime = callTime;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.callerName = callerName;
        this.recipient = recipient;
        this.description = description;
        this.receiver = receiver;
        this.entryDate = entryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCallDate() {
        return callDate;
    }

    public void setCallDate(LocalDate callDate) {
        this.callDate = callDate;
    }

    public LocalTime getCallTime() {
        return callTime;
    }

    public void setCallTime(LocalTime callTime) {
        this.callTime = callTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCallerName() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Instant getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Instant entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PhoneLog phoneLog = (PhoneLog) o;
        return Objects.equals(getId(), phoneLog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PhoneLog{" +
                "id=" + id +
                ", callDate=" + callDate +
                ", callTime=" + callTime +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", callerName='" + callerName + '\'' +
                ", recipient='" + recipient + '\'' +
                ", description='" + description + '\'' +
                ", receiver='" + receiver + '\'' +
                ", entryDate=" + entryDate +
                '}';
    }
}
