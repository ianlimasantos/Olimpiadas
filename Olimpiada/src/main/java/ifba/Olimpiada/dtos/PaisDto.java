package ifba.Olimpiada.dtos;

import ifba.Olimpiada.models.Pais;

public record PaisDto (Long id, String nome){

	public PaisDto(Pais pais) {
		this(pais.getId(), pais.getNome());
	}
}
