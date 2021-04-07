package com.example.Springdesafio.controllers;

import com.example.Springdesafio.services.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {

    @Autowired
    private ServiceImpl service;


    @GetMapping("/articles")
    public ResponseEntity listarProductos(@RequestParam Map<String,String> params) throws IOException {
        return new ResponseEntity(service.listarProductos(params), HttpStatus.OK);
    }

}
