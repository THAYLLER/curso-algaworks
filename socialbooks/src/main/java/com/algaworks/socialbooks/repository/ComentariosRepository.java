/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.socialbooks.repository;

import com.algaworks.socialbooks.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author darthd3v
 */
public interface ComentariosRepository extends JpaRepository<Comentario, Long>{
    
}
