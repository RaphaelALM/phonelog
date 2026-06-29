package com.application.phonelog.repository;

import com.application.phonelog.model.PhoneLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneLogRepository extends JpaRepository<PhoneLog, Long> {

    @Query(value = """  
        SELECT *
        FROM test_log p
        WHERE LOWER(p.company) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(p.telp_no) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(p.caller) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(p.callee) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(p.reason) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(p.taken_by) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR TO_CHAR(p.telp_date, 'YYYY-MM-DD') LIKE CONCAT('%', :keyword, '%')
           OR TO_CHAR(p.telp_time, 'HH24:MI') LIKE CONCAT('%', :keyword, '%')
        ORDER BY p.telp_date DESC, p.telp_time DESC, p.company ASC
        """, nativeQuery = true)
    Page<PhoneLog> search(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = """  
        SELECT *
        FROM test_log p
        WHERE LOWER(p.company) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(p.telp_no) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(p.caller) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(p.callee) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(p.reason) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(p.taken_by) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR TO_CHAR(p.telp_date, 'YYYY-MM-DD') LIKE CONCAT('%', :keyword, '%')
           OR TO_CHAR(p.telp_time, 'HH24:MI') LIKE CONCAT('%', :keyword, '%')
        ORDER BY p.telp_date DESC, p.telp_time DESC, p.company ASC
        """, nativeQuery = true)
    List<PhoneLog> searchForCsv(@Param("keyword") String keyword);
}
