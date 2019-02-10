package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.ComentariosRepository;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exceptions.LivroNaoEncontradoException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivrosService {
    
    @Autowired
    private LivrosRepository livrosRepository;
    @Autowired
    private ComentariosRepository comentariosRepository;
    
    public  List<Livro> listar(){
        
        return livrosRepository.findAll();
    }
    public Livro buscar(Long id) {
            Livro livro = livrosRepository.getOne(id);
            
            if(livro == null) throw  new LivroNaoEncontradoException("O livro não pode ser encontrado");
            
            return livro;
    }
    public Livro salvar(Livro livro) {
        
            livro.setId(null);
            return livrosRepository.save(livro);
    }
    public  void deletar(Long id) {
        
        try {
            
            livrosRepository.deleteById(id);
        } catch (LivroNaoEncontradoException e) {
            
            throw new LivroNaoEncontradoException("O livro não pode ser encontrado");
        }
    }
    
    public void atualizar(Livro livro) {
        
        verificarExistencia(livro.getId());
        livrosRepository.save(livro);
    }
    
    public void verificarExistencia(Long id) {
        
        buscar(id);
    }
    
    public Comentario salvarComentario(Long livroId, Comentario comentario){
        
        Livro livro = buscar(livroId);
        comentario.setLivro(livro);
        comentario.setData(new Date());
        
        return comentariosRepository.save(comentario);
    }
    public List<Comentario> listaeComentario(Long id ) {
        
        Livro livro = buscar(id);
        return  livro.getComentarios();
        
    }
}
