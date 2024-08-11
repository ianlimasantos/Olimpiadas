package ifba.Olimpiada.dtos;

import java.util.List;

import ifba.Olimpiada.models.Pais;

public record PaisDto (Long id, String nome, List<UsuarioDto> usuarios){

	public PaisDto(Pais pais) {
		this(pais.getId(), pais.getNome(), pais.getUsuarios().stream().map(UsuarioDto::new).toList());
	}
}
