package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.ProductEntity;
import com.example.demo.model.Cart;
import com.example.demo.model.CartManager;
@Controller
@RequestMapping("/cart")
public class ShoppingCart {
	@Autowired
    private CartManager cartManager;
    @RequestMapping("/add")
    public String add(HttpSession session,Model modal, @RequestParam("id") ProductEntity product,
                             @RequestParam(value = "qty", required = false, defaultValue = "1") int qty){
        Cart cart = cartManager.getCart(session);
        cart.addItem(product, qty);

        double tong = cart.getTotal();

        modal.addAttribute("tongtien", tong);

        return "shoppingCart";
    }

    @RequestMapping("/remove")
    public String remove(HttpSession session, Model model,@RequestParam("id") ProductEntity product){
        Cart cart = cartManager.getCart(session);
        cart.removeItem(product);
        double tong = cart.getTotal();
        model.addAttribute("tongtien", tong);
        return "shoppingCart";
    }

    @RequestMapping("/update")
    public String update(HttpSession session,Model model, @RequestParam("id") ProductEntity product, @RequestParam("qty") int qty){
        Cart cart = cartManager.getCart(session);
        cart.updateItem(product, qty);
        double tong = cart.getTotal();
        model.addAttribute("tongtien", tong);
        return "shoppingCart";
    }

}
