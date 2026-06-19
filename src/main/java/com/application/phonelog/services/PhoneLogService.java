package com.application.phonelog.services;

import com.application.phonelog.model.PhoneLog;
import com.application.phonelog.repository.PhoneLogRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneLogService {

    private final PhoneLogRepository phoneLogRepository;

    public PhoneLogService(PhoneLogRepository phoneLogRepository) {
        this.phoneLogRepository = phoneLogRepository;
    }

    public List<PhoneLog> getAllPhoneLogs(){
        return phoneLogRepository.findAll(
                Sort.by(
                    Sort.Order.desc("callDate"),
                    Sort.Order.desc("callTime"),
                    Sort.Order.asc("companyName")
                )
        );
    }

    public List<PhoneLog> findByKeyword(String keyword){
        return phoneLogRepository.search(keyword);
    }

    public PhoneLog getPhoneLogById(long id){
        return phoneLogRepository.findById(id).orElseThrow();
    }

    public void addPhoneLog(PhoneLog phoneLog){
        phoneLogRepository.save(phoneLog);
    }

    public void deleteLog(Long id) {
        phoneLogRepository.deleteById(id);
    }


}
