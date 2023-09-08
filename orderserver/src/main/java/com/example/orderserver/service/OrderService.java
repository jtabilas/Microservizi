package com.example.orderserver.service;

import com.example.orderserver.model.OrderServer;
import com.example.orderserver.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;


    public List<OrderServer> getAllOrder() {
        return repo.findAll();
    }

    public Optional<OrderServer> getOrderById(int id) {
        return repo.findById(id);
    }

    public OrderServer addOrder(OrderServer order) {
        return repo.save(order);
    }

    public String deleteOrderById(int id) {
        repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repo.deleteById(id);
        return "Delete order success";
    }

    public OrderServer updateOrder(int id, OrderServer orderServer) {
        OrderServer updatedOrder = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        updatedOrder.setStatusOrder(orderServer.getStatusOrder());
        updatedOrder.setCodeTracking(orderServer.getCodeTracking());
        updatedOrder.setShippedDate(orderServer.getShippedDate());
        
        return repo.save(updatedOrder);
    }

}
