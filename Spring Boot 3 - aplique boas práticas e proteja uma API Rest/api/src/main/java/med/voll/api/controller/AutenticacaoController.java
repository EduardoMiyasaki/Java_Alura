package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.dadosAutenticacao;
import med.voll.api.dto.dadosTokenJWT;
import med.voll.api.model.Usuario;
import med.voll.api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid dadosAutenticacao dados) {
        // passando os dados de login para o DTO do próprio Spring Security
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        // Chamando o AuthenticationManager para autenticar o usuário
        // O AuthenticationManager chama a autenticação para a classe AutenticacaoService,
        // que implementa a interface UserDetailsService.
        // Como a AutenticacaoService implementa UserDetailsService, o Spring Secur ity sabe que deve
        // chamar o método loadUserByUsername, que por sua vez chama o método findByLogin do repository.

        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new dadosTokenJWT(tokenJWT));
    }
}
