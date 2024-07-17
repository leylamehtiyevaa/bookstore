package com.bookstore.inventory_service.controller;


import com.bookstore.inventory_service.dto.InventoryResponse;
import com.bookstore.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    //http://localhost:8082/api/inventory/martin-eden,pride-and-prejudice - this wasn't optimal solution

    //Request Parameter looks like this:
    //http://localhost:8082/api/inventory?skuCode=martin-eden&skuCode=pride-and-prejudice
    //each element inside the list it will create separate request and it will append to url
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
