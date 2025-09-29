package com.vani.restaurant.controller;

import com.vani.restaurant.model.MenuItem;
import com.vani.restaurant.repository.MenuItemRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin
public class MenuController {

    private final MenuItemRepository repo;

    public MenuController(MenuItemRepository repo) { this.repo = repo; }

    @GetMapping
    public List<MenuItem> all() { return repo.findByAvailableTrue(); }

    @PostMapping
    public MenuItem add(@RequestBody MenuItem item) { return repo.save(item); }

    // simple bootstrap endpoint to add sample items
    @PostMapping("/bootstrap")
    public List<MenuItem> bootstrap() {
        MenuItem m1 = new MenuItem("Margherita Pizza", "Classic cheese pizza", new BigDecimal("249.00"));
        MenuItem m2 = new MenuItem("Veg Burger", "Veg patty with lettuce & tomato", new BigDecimal("129.00"));
        MenuItem m3 = new MenuItem("Pasta Alfredo", "Creamy white sauce pasta", new BigDecimal("199.00"));
        repo.save(m1); repo.save(m2); repo.save(m3);
        return repo.findAll();
    }
}
