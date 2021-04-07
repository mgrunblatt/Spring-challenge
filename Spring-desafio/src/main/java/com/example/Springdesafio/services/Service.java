package com.example.Springdesafio.services;

import com.example.Springdesafio.dtos.ArticleDTO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Service {

    public List<ArticleDTO> listarProductos(Map<String,String> params) throws IOException;
}
