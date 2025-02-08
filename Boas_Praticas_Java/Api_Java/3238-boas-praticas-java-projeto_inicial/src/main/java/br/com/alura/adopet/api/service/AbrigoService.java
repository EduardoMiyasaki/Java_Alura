package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDTO;
import br.com.alura.adopet.api.dto.CadastrarPetNoAbrigoDTO;
import br.com.alura.adopet.api.dto.DadosDetalhesPet;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validacoes.AbrigoCadastroExiste;
import br.com.alura.adopet.api.validacoes.AbrigoCadastroPet;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private AbrigoCadastroExiste abrigoCadastroExiste;

    @Autowired
    private AbrigoCadastroPet abrigoCadastroPet;

    public List<Abrigo> listarAbrigos() {
        return abrigoRepository.findAll();
    }

    public void cadastrar(@RequestBody @Valid CadastrarAbrigoDTO dto) {
        abrigoCadastroExiste.validar(dto);
        abrigoRepository.save(new Abrigo(dto));
    }

    public Abrigo listarAbrigoId(Long id) {
        Abrigo abrigo = abrigoRepository.getReferenceById(id);
        if (abrigo == null) {
            throw new ValidacaoException("Nenhum abrigo encontrado pelo ID");
        }
        return abrigo;
    }

    public Abrigo listarAbrigoNome(String nome) {
        Abrigo abrigo = abrigoRepository.findByNome(nome);
        if (abrigo == null) {
            throw new ValidacaoException("Nenhum abrigo encontrado pelo Nome");
        }
        return abrigo;
    }

    public ResponseEntity<List<DadosDetalhesPet>> listarPets(String idOuNome) {
        try {
            return ResponseEntity.ok(listarPetsPeloIDDoAbrigo(idOuNome));
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build();
        } catch (NumberFormatException e) {
            List<DadosDetalhesPet> pets = listarPetsPeloNomeDoAbrigo(idOuNome);
            return ResponseEntity.ok(pets);
        }
    }

    private List<DadosDetalhesPet> listarPetsPeloIDDoAbrigo(String idOuNome) {
        Long id = Long.parseLong(idOuNome);
        Abrigo abrigo = listarAbrigoId(id);
        return converterParaDetalhesDoPet(abrigo.getPets());
    }

    private List<DadosDetalhesPet> listarPetsPeloNomeDoAbrigo(String idOuNome) {
        Abrigo abrigo = listarAbrigoNome(idOuNome);
        return converterParaDetalhesDoPet(abrigo.getPets());
    }

    private List<DadosDetalhesPet> converterParaDetalhesDoPet(List<Pet> pets) {
        List<DadosDetalhesPet> listaDetalhesPet = new ArrayList<>();
        for (Pet pet : pets) {
            listaDetalhesPet.add(new DadosDetalhesPet(pet.getId(), pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade()));
        }
        return listaDetalhesPet;
    }

    public void cadastrarPetnoAbrigo(String idOuNome, DadosDetalhesPet pet) {
        try {
            Long id = Long.parseLong(idOuNome);
            Abrigo abrigo = listarAbrigoId(id);
            abrigoCadastroPet.validar(abrigo, pet);
            cadastrarPet(abrigo, pet);
        } catch (EntityNotFoundException e) {
            Abrigo abrigo = listarAbrigoNome(idOuNome);
            cadastrarPet(abrigo, pet);
        }
    }

    public void cadastrarPet(Abrigo abrigo, DadosDetalhesPet pet) {
        CadastrarPetNoAbrigoDTO cadastroPet = new CadastrarPetNoAbrigoDTO(abrigo, false);
        Pet pet1 = new Pet(pet);
        pet1.cadastrarPetNoAbrigoDTO(cadastroPet);

        abrigo.getPets().add(pet1);
        abrigoRepository.save(abrigo);
    }

}



