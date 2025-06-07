package com.example.e_commerce.controller;



import com.example.e_commerce.entity.Cart;
import com.example.e_commerce.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/carts")
public class CartWebController {

    private final CartService cartService;

    public CartWebController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String getAllCarts(Model model) {
        model.addAttribute("carts", cartService.getAllCarts());
        return "carts/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("cart", new Cart());
        return "carts/create";
    }

    @PostMapping("/create")
    public String createCart(@ModelAttribute Cart cart) {
        cartService.createCart(cart);
        return "redirect:/web/carts";
    }

    @PostMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("cart", cartService.getCartById(id));
        return "carts/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCart(@PathVariable Long id, @ModelAttribute Cart cart) {
        cartService.updateCart(id, cart);
        return "redirect:/web/carts";
    }

    @PostMapping("/delete/{id}")
    public String deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return "redirect:/web/carts";
    }
}