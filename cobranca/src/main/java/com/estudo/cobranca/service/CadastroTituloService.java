/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estudo.cobranca.service;

import com.estudo.cobranca.model.Titulo;
import com.estudo.cobranca.repository.Titulos;
import com.estudo.cobranca.repository.filter.Titulofilter;
import java.util.List;
import static java.util.Locale.filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author darthd3v
 */
@Service
public class CadastroTituloService {
    
    @Autowired
    private Titulos titulos;
    
    public void salvar(Titulo titulo) {
        
        titulos.save(titulo);
    }

    public void excluir(Long codigo) {
        titulos.deleteById(codigo);
    }
    
    public List<Titulo> filtrar(Titulofilter filtro) {
        
        String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
        return titulos.findByDescricaoContaining(descricao);
    }
}
