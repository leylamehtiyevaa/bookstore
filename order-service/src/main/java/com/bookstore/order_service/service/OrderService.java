package com.bookstore.order_service.service;

import com.bookstore.order_service.dto.InventoryResponse;
import com.bookstore.order_service.dto.OrderLineItemsDto;
import com.bookstore.order_service.dto.OrderRequest;
import com.bookstore.order_service.model.Order;
import com.bookstore.order_service.model.OrderLineItems;
import com.bookstore.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository; //saves to db
//    private final WebClient webClient;
    private final WebClient webClient;


    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();

        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

//        System.out.println("SKU Codes: " + skuCodes);
//        System.out.println("OrderLineItemsDto List: " + orderRequest.getOrderLineItemsDtoList());


        //call inventory service and place order to check if product in stock or not
        InventoryResponse[] inventoryResponseArray = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();//.block() - makes a sync request in webClient ;to read data from the webClient response use .bodyToMono()

        boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
               .allMatch(InventoryResponse::isInStock);


        if(allProductsInStock) {
            orderRepository.save(order);
            System.out.println(allProductsInStock);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
