package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private Cart cart;

}
