package ifba.Olimpiada.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "paises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String nome;
	
	@ManyToMany
	@JoinTable(
			name = "InscricaoPais",
			joinColumns = @JoinColumn(name = "Pais_Id"),
			inverseJoinColumns = @JoinColumn(name = "Usuario_Id")
	)
	
	private List<Usuario> usuarios;
}
