package ifba.Olimpiada.dtos;

import ifba.Olimpiada.models.Esporte;
import ifba.Olimpiada.models.Medalha;
import ifba.Olimpiada.models.Pais;
import ifba.Olimpiada.models.TipoMedalha;

public record MedalhaDto (Long id, PaisDto paisDto, EsporteDto esporteDto, TipoMedalha tipoMedalha){

	public MedalhaDto(Medalha medalha) {
		this(medalha.getId(), new PaisDto(medalha.getPais()), new EsporteDto(medalha.getEsporte()), medalha.getTipoMedalha());
	}

}
