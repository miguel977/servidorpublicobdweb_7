package com.abctreinamentos.servidorpublicobdweb.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class Error implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // Tratar erro 404 (Not Found)
                return "/erro/404"; // Nome da página de erro 404 a ser exibida
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // Tratar erro 500 (Internal Server Error)
                return "/erro/500"; // Nome da página de erro 500 a ser exibida
            }
        }

        return "error";
    }


    public String getErrorPath() {
        return "/error";
    }
}

