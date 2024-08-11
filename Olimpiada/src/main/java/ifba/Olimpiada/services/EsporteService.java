package ifba.Olimpiada.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ifba.Olimpiada.dtos.EsporteDto;
import ifba.Olimpiada.models.Esporte;
import ifba.Olimpiada.repositories.EsporteRepository;

@Service
public class EsporteService {

	@Autowired
	private EsporteRepository esporteRepository;
	
	public List<EsporteDto> listarTodos() {
		return esporteRepository.findAll().stream().map(EsporteDto::new).collect(Collectors.toList());
	}
	
	public ResponseEntity<EsporteDto> cadastrar(EsporteDto esporteDto) {
		return ResponseEntity.ok(new EsporteDto(esporteRepository.save(new Esporte(esporteDto))));
	}
	
	public ResponseEntity<?> DeletarPorId(Long id) {
		var op = esporteRepository.findById(id);
		if (op.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		esporteRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
