package com.application.phonelog.repository;

import com.application.phonelog.model.PhoneLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneLogRepository extends JpaRepository<PhoneLog, Long> {
}
