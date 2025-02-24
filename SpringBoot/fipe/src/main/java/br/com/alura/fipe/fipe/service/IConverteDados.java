package br.com.alura.fipe.fipe.service;

public interface IConverteDados {

    <T> T converter(String json,Class <T> T);
}
