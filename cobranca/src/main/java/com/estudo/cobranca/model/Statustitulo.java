/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estudo.cobranca.model;

/**
 *
 * @author darthd3v
 */
public enum Statustitulo {
    
    PENDENTE("Pendente"),
    RECEBIDO("Recebido");
    
    private String descricao;

    public String getDescricao() {
        return descricao;
    }
    
    Statustitulo(String descricao) {
        
        this.descricao = descricao;
    }
}
