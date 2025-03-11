package com.alura.screensoundmusic.serviceInutil;

public interface IConverteDados {

    <T> T converterDados(String json, Class<T> classe);
}
