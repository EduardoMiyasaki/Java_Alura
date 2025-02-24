package br.com.alura.fipe.fipe.service;

import br.com.alura.fipe.fipe.validacao.ValidacaoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {

    private ObjectMapper mapper = new ObjectMapper();

        @Override
        public <T> T converter(String json, Class<T> classe) {
            try {
                return mapper.readValue(json, classe);
            } catch (JsonProcessingException e) {
               throw new ValidacaoException(e.getMessage());
            }
        }

}
