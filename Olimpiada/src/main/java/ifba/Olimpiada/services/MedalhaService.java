package ifba.Olimpiada.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ifba.Olimpiada.dtos.EsporteDto;
import ifba.Olimpiada.dtos.MedalhaDto;
import ifba.Olimpiada.models.Esporte;
import ifba.Olimpiada.models.Medalha;
import ifba.Olimpiada.repositories.EsporteRepository;
import ifba.Olimpiada.repositories.MedalhaRepository;

@Service
public class MedalhaService {

	@Autowired
	private MedalhaRepository medalhaRepository;
	
	public List<MedalhaDto> listarTodos() {
		return medalhaRepository.findAll().stream().map(MedalhaDto::new).collect(Collectors.toList());
	}
	
	public ResponseEntity<MedalhaDto> cadastrar(MedalhaDto medalhaDto) {
		return ResponseEntity.ok(new MedalhaDto(medalhaRepository.save(new Medalha(medalhaDto))));
	}
	
	public ResponseEntity<?> DeletarPorId(Long id) {
		var op = medalhaRepository.findById(id);
		if (op.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		medalhaRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
