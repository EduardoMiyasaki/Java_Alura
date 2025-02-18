package br.com.alura.ScreenMatch.service;

public interface IConverteDados {

    <T> T converterDados(String json, Class<T> classe);
}
