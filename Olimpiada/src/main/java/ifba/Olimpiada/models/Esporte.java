package ifba.Olimpiada.models;

import ifba.Olimpiada.dtos.EsporteDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "esportes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Esporte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String nome;

	public Esporte(EsporteDto esporteDto) {
		this.id = esporteDto.id();
		this.nome = esporteDto.nome();
	}
}
