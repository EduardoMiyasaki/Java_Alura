package com.alura.screensoundmusic.serviceInutil;

import com.alura.screensoundmusic.validacao.ValidacaoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados{

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T converterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        }catch(JsonProcessingException e){
            throw new ValidacaoException(e.getMessage());
        }
    }
}
