package ifba.Olimpiada.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
