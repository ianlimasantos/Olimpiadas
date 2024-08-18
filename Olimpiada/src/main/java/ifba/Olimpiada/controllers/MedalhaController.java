package ifba.Olimpiada.controllers;

import java.awt.print.Pageable;
import java.util.List;

import org.hibernate.query.Page;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.Olimpiada.dtos.CriarMedalhaDto;
import ifba.Olimpiada.dtos.MedalhaDto;
import ifba.Olimpiada.dtos.PaisEmailDto;
import ifba.Olimpiada.services.AssociacaoService;
import ifba.Olimpiada.services.MedalhaService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/medalha")
public class MedalhaController {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	MedalhaService medalhaService;
	
	@Autowired
	AssociacaoService associacaoService;
	
	@GetMapping
	public List<MedalhaDto> listar() {
			
		return medalhaService.listarTodos();
	}
	
	@PostMapping
	public ResponseEntity<MedalhaDto> salvar(@RequestBody CriarMedalhaDto medalhaDto) {
        var result = medalhaService.cadastrar(medalhaDto);
        if(result.getStatusCode().is2xxSuccessful()) {
        	PaisEmailDto paisEmailDto = associacaoService.obterPaisComEmails(medalhaDto.paisId());
        	var usuarios = associacaoService.obterUsuariosPorPais(medalhaDto.paisId());
        	this.rabbitTemplate.convertAndSend("email.notificacao", paisEmailDto);
        	
        }
        return result;
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MedalhaDto> atualizar(@RequestBody  MedalhaDto medalhaDto, @PathVariable Long id) {

		return medalhaService.atualizar(medalhaDto, id);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	//@Secured("ROLE_ADMINPONTO")
	public ResponseEntity<?> deletar(@PathVariable Long id) {

		return medalhaService.DeletarPorId(id);
	}
}
