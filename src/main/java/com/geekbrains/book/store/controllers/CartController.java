package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.services.OrderService;
import com.geekbrains.book.store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private Cart cart;
    private UserService userService;
    private OrderService orderService;

    @GetMapping
    public String cartAll(Model model
    ) {
        model.addAttribute("orderItems", cart.getOrderItems());
        return "cart-page";
    }

    @GetMapping("/add")
    public String add(Model model,
                      @RequestParam(name = "bookid") Long id
    ) {
        cart.add(id);
        return "redirect:/books";
    }

    @GetMapping("/confirm")
    public String confirm(Model model,
                          Principal principal
    ) {
        if(principal == null){
            return "redirect:/login";
        }
        User user = userService.findByUsername(principal.getName()).get();

        orderService.makeOrder(cart, user);
//        cart.makeOrder(user);
        return "redirect:/books";
    }

}
