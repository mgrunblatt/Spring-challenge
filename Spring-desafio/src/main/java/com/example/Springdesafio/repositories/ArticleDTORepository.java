package com.example.Springdesafio.repositories;

import com.example.Springdesafio.dtos.ArticleDTO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ArticleDTORepository {

    public List<ArticleDTO> findProductsByCategory(List<ArticleDTO> articleDTOS, Map<String,String> params) throws IOException;
    public List<ArticleDTO> findProductsByCategories(List<ArticleDTO> articleDTOS, String category, String freeShipping);

}
