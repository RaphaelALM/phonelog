package com.application.phonelog.exeptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PhoneLogNotFoundException.class)
    public String handlePhoneLogNotFound(
            PhoneLogNotFoundException ex,
            Model model) {

        model.addAttribute("errorTitle", "データが見つかれません");
        model.addAttribute("errorMessage", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(
            Exception ex,
            Model model){

        model.addAttribute("errorTitle", "システムエラー");
        model.addAttribute("errorMessage", "予期しないエラーが発生しました。");

        return "error";
    }
}
