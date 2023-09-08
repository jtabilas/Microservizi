package com.example.orderserver.controller;


import com.example.orderserver.model.OrderServer;
import com.example.orderserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService service;

    //get all order
    @GetMapping("/order")
    public List<OrderServer> getAllOrder() {
        return service.getAllOrder();
    }


    //get order by id
    @GetMapping("/order/{id}")
    public Optional<OrderServer> getOrderById(@PathVariable int id) {
        return service.getOrderById(id);
    }

    //add order
    @PostMapping("/order")
    public OrderServer addOrder(@RequestBody OrderServer order) {
        return service.addOrder(order);
    }

    //delete order
    @DeleteMapping("/order/{id}")
    public String deleteOrderById(@PathVariable int id) {
      return  service.deleteOrderById(id);
    }

    //update order
    @PutMapping("/order/{id}")
    public OrderServer updateOrder(@PathVariable int id, @RequestBody OrderServer orderServer) {
        return service.updateOrder(id, orderServer);
    }


}
