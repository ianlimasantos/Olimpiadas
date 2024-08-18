package ifba.Olimpiada.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifba.Olimpiada.dtos.PaisEmailDto;
import ifba.Olimpiada.models.Pais;
import ifba.Olimpiada.models.Usuario;
import ifba.Olimpiada.repositories.PaisRepository;
import ifba.Olimpiada.repositories.UsuarioRepository;

@Service
public class AssociacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PaisRepository paisRepository;

    public void associarUsuarioComPais(Long usuarioId, Long paisId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        Pais pais = paisRepository.findById(paisId)
            .orElseThrow(() -> new RuntimeException("Pais não encontrado"));

        usuario.getPais().add(pais);
        pais.getUsuarios().add(usuario);

        usuarioRepository.save(usuario);
    }
    
    public List<Usuario> obterUsuariosPorPais(Long paisId) {
        Pais pais = paisRepository.findById(paisId)
            .orElseThrow(() -> new RuntimeException("Pais não encontrado"));

        return pais.getUsuarios();
    }
    
    public PaisEmailDto obterPaisComEmails(Long paisId) {
        Pais pais = paisRepository.findById(paisId)
            .orElseThrow(() -> new RuntimeException("País não encontrado"));

        
        List<String> emails = pais.getUsuarios().stream()
            .map(usuario -> usuario.getEmail()) //
            .toList();

        return new PaisEmailDto(pais.getNome(), emails);
    }
}
