package com.application.phonelog.exeptions;

public class PhoneLogNotFoundException extends RuntimeException {
    public PhoneLogNotFoundException(Long id){
        super("ID" + id + "が存在しません。");
    }
}
