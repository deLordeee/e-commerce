package com.example.e_commerce.controller;



import com.example.e_commerce.entity.Order;
import com.example.e_commerce.entity.OrderStatus;
import com.example.e_commerce.entity.PaymentMethod;
import com.example.e_commerce.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/orders")
public class OrderWebController {

    private final OrderService orderService;

    public OrderWebController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("statuses", OrderStatus.values());
        model.addAttribute("paymentMethods", PaymentMethod.values());
        return "orders/create";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute Order order, Model model) {

        try {
            orderService.createOrder(order);
            return "redirect:/web/orders";
        } catch (Exception e) {

            model.addAttribute("error", "Failed to create order: " + e.getMessage());
            model.addAttribute("order", order);
            model.addAttribute("statuses", OrderStatus.values());
            model.addAttribute("paymentMethods", PaymentMethod.values());
            return "orders/create";
        }
    }

    @PostMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        model.addAttribute("statuses", OrderStatus.values());
        model.addAttribute("paymentMethods", PaymentMethod.values());
        return "orders/edit";
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute Order order) {
        orderService.updateOrder(id, order);
        return "redirect:/web/orders";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/web/orders";
    }
}