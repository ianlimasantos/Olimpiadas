package ifba.Olimpiada.dtos;

import ifba.Olimpiada.models.TipoMedalha;

public record CriarMedalhaDto(Long paisId, Long esporteId, TipoMedalha tipoMedalha) {

}
