package ifba.Olimpiada.models;

import ifba.Olimpiada.dtos.EsporteDto;
import ifba.Olimpiada.dtos.MedalhaDto;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "medalhas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medalha {

	public Long Id;
	public TipoMedalha tipoMedalha;
	@OneToMany
	public Pais pais;
	@OneToMany
	public Esporte esporte;
	
	public Medalha(MedalhaDto medalhaDto) {
		this.Id = medalhaDto.id();
		this.esporte = new Esporte(medalhaDto.esporteDto());
		this.pais = new Pais(medalhaDto.paisDto());
	}
	
}
