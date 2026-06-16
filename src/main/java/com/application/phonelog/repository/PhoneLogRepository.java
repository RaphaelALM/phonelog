package com.application.phonelog.repository;

import com.application.phonelog.model.PhoneLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneLogRepository extends JpaRepository<PhoneLog, Long> {

    @Query(value = """
                SELECT p
                FROM PhoneLog p
                WHERE LOWER(p.companyName) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(p.phoneNumber) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(p.callerName) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(p.recipient) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(p.receiver) LIKE LOWER(CONCAT('%', :keyword, '%'))
                ORDER BY p.callDate DESC, p.callTime DESC
            """)

     List<PhoneLog> search(@Param("keyword") String keyword);

}
