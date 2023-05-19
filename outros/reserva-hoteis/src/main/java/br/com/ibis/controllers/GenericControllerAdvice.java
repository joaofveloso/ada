package br.com.ibis.controllers;

import br.com.ibis.services.exceptions.ClienteMenorDeIdadeException;
import br.com.ibis.services.exceptions.CodigoDiferenteException;
import br.com.ibis.services.exceptions.EmailJaEstaSenUsadoException;
import br.com.ibis.services.exceptions.EmailNaoCadastradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ControllerAdvice
public class GenericControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        System.out.println(ex.getMessage());
    }

    @ExceptionHandler(ClienteMenorDeIdadeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleClienteMenorDeIdadeException(ClienteMenorDeIdadeException ex) {
        System.out.println(ex.getMessage());
    }

    @ExceptionHandler(CodigoDiferenteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleCodigoDiferenteException(CodigoDiferenteException ex) {
        System.out.println(ex.getMessage());
    }


    @ExceptionHandler(EmailJaEstaSenUsadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleEmailJaEstaSenUsadoException(EmailJaEstaSenUsadoException ex) {
        System.out.println(ex.getMessage());
    }

    @ExceptionHandler(EmailNaoCadastradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEmailNaoCadastradoException(EmailNaoCadastradoException ex) {
        System.out.println(ex.getMessage());
    }
}
