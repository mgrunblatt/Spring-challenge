package com.example.Springdesafio.services;

import com.example.Springdesafio.dtos.*;
import com.example.Springdesafio.exceptions.ArticleDTONotFoudException;
import com.example.Springdesafio.exceptions.ExceededArgumentLimitException;
import com.example.Springdesafio.repositories.ArticleDTORepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDTORepositoryImpl articleDTORepository;

    @Override
    public List<ArticleDTO> listarProductos(Map<String,String> params) throws IOException, ExceededArgumentLimitException, ArticleDTONotFoudException {
        List<ArticleDTO> articleDTOS=articleDTORepository.loadDataBase();
        for (int i =0; i < articleDTOS.size();i++){
            if(articleDTOS.get(i).getFreeShipping().equals("SI")){
                articleDTOS.get(i).setFreeShipping("true");
            }else{
                articleDTOS.get(i).setFreeShipping("false");
            }
        }

        if(params.isEmpty())
            return articleDTOS;
        else
            switch (params.size()){
                case 1: articleDTOS = articleDTORepository.findProductsByCategory(articleDTOS, params);
                    break;
                case 2: articleDTOS = articleDTORepository.findProductsByCategories(articleDTOS, params);
                    break;
                case 3: articleDTOS = articleDTORepository.findProductsByCategoriesAndOrder(articleDTOS, params);
                    break;
                default: throw new ExceededArgumentLimitException("too many arguments. Max: 3 -> (2 filters, 1 order)");
            }
            return articleDTOS;
    }


}
