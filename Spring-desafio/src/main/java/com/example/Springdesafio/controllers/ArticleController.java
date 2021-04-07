package com.example.Springdesafio.controllers;

import com.example.Springdesafio.exceptions.ArticleDTONotFoudException;
import com.example.Springdesafio.exceptions.ExceededArgumentLimitException;
import com.example.Springdesafio.services.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl service;


    /*
    Allows to get the whole list without arguments or get filter by category, or by a combination of two filters
    and also a order. Filters have to be with the first upper case first letter.
    Example: ocalhost:8081/api/v1/articles?category=Indumentaria&brand=Taverniti&freeShipping=true&order=0
    */
    @GetMapping("/articles")
    public ResponseEntity listarProductos(@RequestParam Map<String,String> params) throws IOException, ExceededArgumentLimitException, ArticleDTONotFoudException {
        return new ResponseEntity(service.listarProductos(params), HttpStatus.OK);
    }



}
