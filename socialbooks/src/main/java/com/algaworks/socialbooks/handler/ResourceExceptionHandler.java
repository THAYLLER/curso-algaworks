/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.socialbooks.handler;

import com.algaworks.socialbooks.services.exceptions.LivroNaoEncontradoException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<Void> HandlerLivroNãoEncontradoException(LivroNaoEncontradoException e, HttpServletRequest request) {
    
        return ResponseEntity.notFound().build();
    }
}
