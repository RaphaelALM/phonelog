package com.application.phonelog.services;

import com.application.phonelog.exeptions.PhoneLogNotFoundException;
import com.application.phonelog.model.PhoneLog;
import com.application.phonelog.repository.PhoneLogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneLogService {

    private final PhoneLogRepository phoneLogRepository;

    public PhoneLogService(PhoneLogRepository phoneLogRepository) {
        this.phoneLogRepository = phoneLogRepository;
    }

//    public List<PhoneLog> getAllPhoneLogs(){
//        return phoneLogRepository.findAll(
//                Sort.by(
//                    Sort.Order.desc("callDate"),
//                    Sort.Order.desc("callTime"),
//                    Sort.Order.asc("companyName")
//                )
//        );
//    }
//
//    public List<PhoneLog> findByKeyword(String keyword){
//        return phoneLogRepository.search(keyword);
//    }


    public Page<PhoneLog> getAllPhoneLogs(int page){

        Pageable pageable = PageRequest.of(
                page,
                20,
                Sort.by(
                        Sort.Order.desc("callDate"),
                        Sort.Order.desc("callTime"),
                        Sort.Order.asc("companyName")
                )
        );

        return phoneLogRepository.findAll(pageable);
    }

    public Page<PhoneLog> findByKeyword(String keyword, int page){

        Pageable pageable = PageRequest.of(
                page,
                20
        );

        return phoneLogRepository.search(keyword, pageable);
    }

    public List<PhoneLog> getAllPhoneLogsForCsv(){

        return phoneLogRepository.findAll(
                Sort.by(
                        Sort.Order.desc("callDate"),
                        Sort.Order.desc("callTime"),
                        Sort.Order.asc("companyName")
                )
        );
    }

    public List<PhoneLog> findByKeywordForCsv(String keyword){

        return phoneLogRepository.searchForCsv(keyword);
    }
    public PhoneLog getPhoneLogById(long id){
        return phoneLogRepository.findById(id).orElseThrow(() -> new PhoneLogNotFoundException(id));
    }

    public PhoneLog addPhoneLog(PhoneLog phoneLog){
        return phoneLogRepository.save(phoneLog);
    }

    public void deleteLog(Long id) {
         phoneLogRepository.deleteById(id);
    }


}
