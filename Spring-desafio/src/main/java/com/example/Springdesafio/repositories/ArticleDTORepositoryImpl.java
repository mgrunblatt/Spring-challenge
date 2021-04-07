package com.example.Springdesafio.repositories;

import com.example.Springdesafio.dtos.ArticleDTO;
import com.example.Springdesafio.dtos.ResponseArticleDTO;
import com.example.Springdesafio.exceptions.ArticleDTONotFoudException;
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
    public List<ArticleDTO> findProductsByCategory(List<ArticleDTO> articleDTOS, Map<String, String> params) throws IOException, ArticleDTONotFoudException {
        List<ArticleDTO> articleDTOList=new ArrayList<>();
        if (articleDTOS != null){
            articleDTOList = articleDTOS.stream()
                    .filter(articleDTO -> articleDTO.getCategory().equals(params.get("category")))
                    .collect(Collectors.toList());
        }

        return articleDTOList;
    }

    @Override
    public List<ArticleDTO> findProductsByCategories(List<ArticleDTO> articleDTOS,Map<String,String> params) throws IOException {
        List<ArticleDTO> articleDTOList=new ArrayList<>();

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
    public List<ArticleDTO> findProductsByCategoriesAndOrder(List<ArticleDTO> articleDTOS, Map<String,String> params) throws IOException, ArticleDTONotFoudException {
        List<ArticleDTO> articleDTOList=findProductsByCategories(articleDTOS,params);

        switch (params.get("order")){
            case "0": articleDTOList = sortAlphaAsc(articleDTOList);
            break;
            case "1": articleDTOList = sortAlphaDesc(articleDTOList);
                break;
            case "2": articleDTOList = sortAlphaHighestPrice(articleDTOList);
                break;
            case "3": articleDTOList = sortAlphaLowestPrice(articleDTOList);
                break;
        }

        return articleDTOList;
    }


    @Override
    public ArticleDTO findArticleByName(String name) throws ArticleDTONotFoudException {
        List<ArticleDTO> articleDTOS = null;
        articleDTOS = loadDataBase();
        ArticleDTO result = null;
        if (articleDTOS != null){
            Optional<ArticleDTO> item = articleDTOS.stream()
                    .filter(articleDTO -> articleDTO.getName().equals(name))
                    .findFirst();
            if (item.isPresent())
                result = item.get();
            else throw new ArticleDTONotFoudException(name);
        }

        return result;
    }

    /*public List<ArticleDTO> loadDataBase() throws IOException {
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

        *//*List<ArticleDTO> dbArticles = null;

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
        }*//*

        return articleDTOS;


    }*/

    public List<ArticleDTO> loadDataBase() {
        FileReader fileReader = null;
        List<ArticleDTO> articles = new ArrayList<>();

        try {
            fileReader = new FileReader("src/main/resources/dbProductos.csv");

            BufferedReader csvReader = new BufferedReader(fileReader);

            String row;
            boolean firstTime = true;
            int idCounter=1;
            while ((row = csvReader.readLine()) != null) {
                if (firstTime) {
                    firstTime = false;
                } else {
                    String[] data = row.split(",");
                    articles.add(objectMapper(data,idCounter));
                    idCounter++;
                }
            }
            csvReader.close();

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            //throw FileNotFound
        } catch (IOException e) {
            e.printStackTrace();
        }

        return articles;
    }

    private ArticleDTO objectMapper(String[] data, int idCounter) {

        String name, category, brand, price, quantity, freeShipping, prestige;

        name = data[0];
        category = data[1];
        brand = data[2];
        price = data[3];
        quantity = data[4];
        freeShipping = data[5];
        prestige = data[6];

        return new ArticleDTO(idCounter,name, category, brand,
                Integer.parseInt(price.replaceAll("[^0-9]","")),
                Integer.parseInt(quantity),
                freeShipping, prestige);

    }

    public List<ArticleDTO> sortAlphaAsc(List<ArticleDTO> articles){
        articles.sort(Comparator.comparing(ArticleDTO::getName));

        return articles;
    }

    public List<ArticleDTO> sortAlphaDesc(List<ArticleDTO> articles){
        articles.sort(Comparator.comparing(ArticleDTO::getName).reversed());

        return articles;
    }

    public List<ArticleDTO> sortAlphaHighestPrice(List<ArticleDTO> articles){
        articles.sort(Comparator.comparing(ArticleDTO::getPrice));

        return articles;
    }

    public List<ArticleDTO> sortAlphaLowestPrice(List<ArticleDTO> articles){
        articles.sort(Comparator.comparing(ArticleDTO::getPrice).reversed());

        return articles;
    }
}
