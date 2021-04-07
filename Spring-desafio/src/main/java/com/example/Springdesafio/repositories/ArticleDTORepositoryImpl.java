package com.example.Springdesafio.repositories;

import com.example.Springdesafio.dtos.ArticleDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.var;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ArticleDTORepositoryImpl implements ArticleDTORepository{

    @Override
    public List<ArticleDTO> findProductsByCategory(List<ArticleDTO> articleDTOS,Map<String,String> params) throws IOException {
        List<ArticleDTO> articleDTOList=new ArrayList<>();
        ArticleDTO result = null;

        String keys[]=new String[params.size()];
        int i=0;
        for(String param: params.keySet()){
            keys[i]=param;
            i++;
        }

        if (articleDTOS != null){
                articleDTOList = articleDTOS.stream()
                        .filter(articleDTO -> articleDTO.getAttribute(keys[0]).equals(params.get(keys[0]))
                        && articleDTO.getAttribute(keys[1]).equals(params.get(keys[1])))
                        .collect(Collectors.toList());
        }

        return articleDTOList;
    }

    @Override
    public List<ArticleDTO> findProductsByCategories(List<ArticleDTO> articleDTOS, String category, String freeShipping) {
        List<ArticleDTO> articleDTOList=new ArrayList<>();
        ArticleDTO result = null;
        if (articleDTOS != null){
            articleDTOList = articleDTOS.stream()
                    .filter(articleDTO -> articleDTO.getCategory().equals(category) &&articleDTO.getFreeShipping().equals(freeShipping) )
                    .collect(Collectors.toList());
        }

        return articleDTOList;
    }

    public List<ArticleDTO> loadDataBase() throws IOException {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:dbProductos.json");
        }catch (Exception e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ArticleDTO>> typeRef = new TypeReference<List<ArticleDTO>>() {};
        List<ArticleDTO> articleDTOS = null;

        try {
            articleDTOS = objectMapper.readValue(file, typeRef);

        }catch (Exception e){
            e.printStackTrace();
        }

        /*List<ArticleDTO> dbArticles = null;

        InputStream resources = new ClassPathResource("dbProductos.csv").getInputStream();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(resources))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                ArticleDTO articleDTO= new ArticleDTO();
                articleDTO.setName(values[0]);
                articleDTO.setCategory(values[1]);
                articleDTO.setBrand(values[2]);
                articleDTO.setPrice(values[3]);
                articleDTO.setQuantity(values[4]);
                articleDTO.setFreeShipping(values[5]);
                articleDTO.setPrestige(values[6]);
                dbArticles.add(articleDTO);
            }
        }*/

        return articleDTOS;


    }

}
