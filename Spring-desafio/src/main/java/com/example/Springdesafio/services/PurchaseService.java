package com.example.Springdesafio.services;

import com.example.Springdesafio.dtos.TicketDTO;
import com.example.Springdesafio.dtos.TicketResponseDTO;
import com.example.Springdesafio.exceptions.ArticleDTONotFoudException;

public interface PurchaseService {

    public TicketResponseDTO procesarCompra(TicketDTO ticketDTO) throws ArticleDTONotFoudException;
}
