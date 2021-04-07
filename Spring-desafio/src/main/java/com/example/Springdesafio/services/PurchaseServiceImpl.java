package com.example.Springdesafio.services;

import com.example.Springdesafio.dtos.*;
import com.example.Springdesafio.exceptions.ArticleDTONotFoudException;
import com.example.Springdesafio.repositories.ArticleDTORepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    private ArticleDTORepositoryImpl articleDTORepository;

    @Override
    public TicketResponseDTO procesarCompra(TicketDTO ticketDTO) throws ArticleDTONotFoudException {
        int countId=1;
        double priceAcum=0;
        List<ResponseArticleDTO> responseArticleDTOList=new ArrayList<>();
        try {
            for (int i = 0; i < ticketDTO.getArticles().size(); i++) {
                ArticleDTO articleDTO = articleDTORepository.findArticleByName(ticketDTO.getArticles().get(i).getName());
                ResponseArticleDTO responseArticleDTO = new ResponseArticleDTO();
                responseArticleDTO.setProductId(articleDTO.getProductId());
                responseArticleDTO.setName(articleDTO.getName());
                responseArticleDTO.setBrand(articleDTO.getBrand());
                responseArticleDTO.setQuantity(articleDTO.getQuantity());
                responseArticleDTOList.add(responseArticleDTO);
                priceAcum += articleDTO.getPrice();

            }
        }catch (ArticleDTONotFoudException e){
            e.printStackTrace();
            return new TicketResponseDTO(null, new StatusCodeDTO(404, "Invalid article. "+e.getMessage()+" not found"));

        }
        ticketDTO.setId(countId);
        ticketDTO.setArticles(responseArticleDTOList);
        ticketDTO.setTotal(priceAcum);

        return new TicketResponseDTO(ticketDTO,
                new StatusCodeDTO(200,"La solicitud de compra se completó con exito"));
    }
}
