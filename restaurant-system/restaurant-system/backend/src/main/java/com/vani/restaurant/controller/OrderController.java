package com.vani.restaurant.controller;

import com.vani.restaurant.model.Order;
import com.vani.restaurant.model.OrderItem;
import com.vani.restaurant.model.OrderStatus;
import com.vani.restaurant.repository.OrderRepository;
import com.vani.restaurant.repository.MenuItemRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    private final OrderRepository orderRepo;
    private final MenuItemRepository menuRepo;

    public OrderController(OrderRepository orderRepo, MenuItemRepository menuRepo) {
        this.orderRepo = orderRepo;
        this.menuRepo = menuRepo;
    }

    @GetMapping
    public List<Order> list() { return orderRepo.findAll(); }

    @PostMapping
    public Order create(@RequestBody Order order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.RECEIVED);
        // link items to order
        if(order.getItems() != null) {
            for(OrderItem it : order.getItems()) {
                it.setOrder(order);
            }
        }
        return orderRepo.save(order);
    }

    @PutMapping("/{id}/status")
    public Order updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        Order o = orderRepo.findById(id).orElseThrow();
        o.setStatus(status);
        return orderRepo.save(o);
    }
}
