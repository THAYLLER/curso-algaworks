package com.estudo.cobranca.repository;

import com.estudo.cobranca.model.Titulo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Titulos extends JpaRepository<com.estudo.cobranca.model.Titulo, Long>{
    
    public List<Titulo> findByDescricaoContaining(String descricao);
    
}
