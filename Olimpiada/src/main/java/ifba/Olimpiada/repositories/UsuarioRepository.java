package ifba.Olimpiada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifba.Olimpiada.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
