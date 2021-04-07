package com.example.Springdesafio.controllers;

import com.example.Springdesafio.dtos.TicketDTO;
import com.example.Springdesafio.exceptions.ArticleDTONotFoudException;
import com.example.Springdesafio.services.ArticleServiceImpl;
import com.example.Springdesafio.services.PurchaseService;
import com.example.Springdesafio.services.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PurchaseController {

    @Autowired
    private PurchaseServiceImpl service;

    /*
    Allows a POST with a TicketDTO object with a list of ResponseArticleDTO inside.
    */
    @PostMapping("/purchase-request")
    public ResponseEntity realizarCompra(@RequestBody TicketDTO ticketDTO) throws ArticleDTONotFoudException {
        return new ResponseEntity(service.procesarCompra(ticketDTO), HttpStatus.OK);
    }
}
