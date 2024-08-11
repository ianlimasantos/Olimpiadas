package ifba.Olimpiada.dtos;

import ifba.Olimpiada.models.Esporte;
import ifba.Olimpiada.models.Pais;

public record EsporteDto (Long id, String nome){

	public EsporteDto(Esporte esporte) {
		this(esporte.getId(), esporte.getNome());
	}
}
