package com.example.Springdesafio.services;

import com.example.Springdesafio.dtos.ArticleDTO;
import com.example.Springdesafio.repositories.ArticleDTORepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{

    @Autowired
    private ArticleDTORepositoryImpl articleDTORepository;

    @Override
    public List<ArticleDTO> listarProductos(Map<String,String> params) throws IOException {
        Long idCount=1L;
        List<ArticleDTO> articleDTOS=articleDTORepository.loadDataBase();
        for (int i =0; i < articleDTOS.size();i++){
            articleDTOS.get(i).setProductId(i+1);
            if(articleDTOS.get(i).getFreeShipping().equals("SI")){
                articleDTOS.get(i).setFreeShipping("true");
            }else{
                articleDTOS.get(i).setFreeShipping("false");
            }
        }

        if(params.isEmpty())
            return articleDTOS;
        else
            if(params.size()==2) {
                return articleDTORepository.findProductsByCategory(articleDTOS, params);
            }else {
                return null;
            }


    }
}
