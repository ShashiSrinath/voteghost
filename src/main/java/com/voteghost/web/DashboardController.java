package com.voteghost.web;

import com.voteghost.domain.Product;
import com.voteghost.domain.User;
import com.voteghost.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    private ProductRepository productRepo;

    @GetMapping("/")
    public String rootView() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal User user, ModelMap model) {
        List<Product> products = productRepo.findByUser(user);
        model.put("products", products);


        return "dashboard";
    }
}
