package ifba.Olimpiada.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import ifba.Olimpiada.dtos.AssociacaoUsuarioPaisDto;
import ifba.Olimpiada.dtos.DadosAutenticacao;
import ifba.Olimpiada.dtos.DadosTokenJWT;
import ifba.Olimpiada.models.Usuario;
import ifba.Olimpiada.services.AssociacaoService;
import ifba.Olimpiada.services.JWTokenService;
import ifba.Olimpiada.services.UsuarioService;

@RestController
@RequestMapping("/login")
public class UsuarioController {

	
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JWTokenService tokenService;
	
	@Autowired
    private UsuarioService usuarioService;
	
	@Autowired
	private AssociacaoService associacaoService;
	
	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
	
	@PostMapping("/register")
    public ResponseEntity cadastrarUsuario(@RequestBody DadosAutenticacao dados) {
        Usuario usuario = usuarioService.cadastrarUsuario(dados);
        return ResponseEntity.ok(usuario);
    }
	
	@PostMapping("/associar-pais")
    public ResponseEntity<?> associarUsuarioComPais(@RequestBody AssociacaoUsuarioPaisDto associacaoDto) {
        associacaoService.associarUsuarioComPais(associacaoDto.usuarioId(), associacaoDto.paisId());
        return ResponseEntity.ok("Usuário associado ao país com sucesso!");
    }
}
