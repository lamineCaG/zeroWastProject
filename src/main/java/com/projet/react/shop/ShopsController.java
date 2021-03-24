package com.projet.react.shop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShopsController {
    private ShopsService shopsService;
    private ShopsRepository shopsRepository;

    @GetMapping("/shops")
    public List<Shops> findShops(){
        return shopsRepository.findAll();
    }

    @GetMapping("/shops/{id}")
    public Shops findShop(Long id){
        return shopsRepository.findShopById(id);
    }

    @PostMapping("")
    public String createShop(@RequestParam Shops shops){
        if (shops == null) {
            shopsRepository.save(shops);
        }
        return "le shops est sauvegarder";
    }
}
