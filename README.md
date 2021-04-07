# Spring-challenge

ENDPOINTS

localhost:8081/api/v1

1- Listado de productos disponibles: GET  /articles

2- Listado de productos filtrados por categoría Ej: GET articles?category=Indumentaria

3- Un listado que permita la combinación de cualquiera de los filtros: 
GET /articles?category=indumentaria&freeShipping=false

4- Orden Albabético ascendente y descendente : 
GET /articles?category=indumentaria&freeShipping=false&order=0
GET /articles?category=indumentaria&freeShipping=false&order=1

5- Orden por mayor precio: GET /articles?category=indumentaria&freeShipping=false&order=2

6- Orden por menor precio: GET /articles?category=indumentaria&freeShipping=false&order=3

7- Solicitud de compra y devolución de ticket con código de status:
POST: /purchase-request 
Payload Ej:

{
    "articles":
    [
        {
            "productId":10,
            "name":"Desmalezadora",
            "brand":"Makita",
            "quantity":1
        },
        {
            "productId":5,
            "name":"Zapatillas Deportivas",
            "brand":"Nike",
            "quantity":2
        }
    ]
}