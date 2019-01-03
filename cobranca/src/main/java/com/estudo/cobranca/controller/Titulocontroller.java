/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estudo.cobranca.controller;

import com.estudo.cobranca.model.Statustitulo;
import com.estudo.cobranca.model.Titulo;
import com.estudo.cobranca.repository.Titulos;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("titulos")
public class Titulocontroller {
    
    @Autowired
    private Titulos titulos;
    
    @RequestMapping("/novo")
    public ModelAndView novo() {
        
        ModelAndView mv = new ModelAndView("cadastroTitulo");
        mv.addObject("todosStatusTitulo",Statustitulo.values());
        
        return mv;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView salvar(Titulo titulo) {
        
        titulos.save(titulo);
        
        ModelAndView mv = new ModelAndView("cadastroTitulo");
        mv.addObject("mensagem","Título salvo com sucesso");
        return mv;
    }
    
    @ModelAttribute("todosStatusTitulo")
    public List<Statustitulo> todosStatusTitulo() {
        
        return Arrays.asList(Statustitulo.values());
    }
    
    @RequestMapping
    public ModelAndView pesquisar() {
        
        List<Titulo> todosTitulos = titulos.findAll();
        ModelAndView mv = new ModelAndView("PesquisaTitulos");
        mv.addObject("titulos",todosTitulos);
        return mv;
    }
    
    
}
