package br.com.eduardo;

public record EnderecoDTO(String cep, String logradouro, String bairro,
                          String localidade, String uf, String estado, String regiao) {
}
