/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estudo.cobranca.repository;

import com.estudo.cobranca.repository.filter.Titulofilter;
import com.estudo.cobranca.model.Statustitulo;
import com.estudo.cobranca.model.Titulo;
import com.estudo.cobranca.repository.Titulos;
import com.estudo.cobranca.service.CadastroTituloService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("titulos")
public class Titulocontroller {
       
    private static final String CADASTRO_VIEW = "cadastroTitulo";
    
    @Autowired
    private Titulos titulos;
    
    @Autowired
    private CadastroTituloService cadastrotituloService;
    
    @RequestMapping("/novo")
    public ModelAndView novo() {
        
        ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
        mv.addObject(new Titulo());
        
        return mv;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String salvar(@Validated Titulo titulo, Errors errors,RedirectAttributes attributes) {
        
        
        if(errors.hasErrors()) {
            
            return "CADASTRO_VIEW";
        }
        
        try {
            
            cadastrotituloService.salvar(titulo);
        
            attributes.addFlashAttribute("mensagem","Título salvo com sucesso");

            return "redirect:/titulos/novo";
        } catch (DataIntegrityViolationException e) {
            
            errors.rejectValue("dataVencimento", null,"Formato de data inválido");
            
            return CADASTRO_VIEW;
        }
    }
    
    @ModelAttribute("todosStatusTitulo")
    public List<Statustitulo> todosStatusTitulo() {
        
        return Arrays.asList(Statustitulo.values());
    }
    
    @RequestMapping
    public ModelAndView pesquisar(@ModelAttribute("filtro") Titulofilter filter) {
        
        List<Titulo> todosTitulos = cadastrotituloService.filtrar(filter);
        ModelAndView mv = new ModelAndView("PesquisaTitulos");
        mv.addObject("titulos",todosTitulos);
        return mv;
    }
    

    @RequestMapping("{codigo}")
    public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
            ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
            mv.addObject(titulo);
            return mv;
    }
    
    @RequestMapping(value="{codigo}",method = RequestMethod.DELETE)
    public String excluir(@PathVariable Long codigo,RedirectAttributes attributes) {
        
        cadastrotituloService.excluir(codigo);
        
        attributes.addFlashAttribute("mensagem","Título excluido com sucesso");
        
        return "redirect:/titulos";
    }
    
}
