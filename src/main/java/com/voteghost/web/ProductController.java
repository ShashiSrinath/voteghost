package com.voteghost.web;

import com.voteghost.domain.Product;
import com.voteghost.domain.User;
import com.voteghost.repositories.ProductRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepo;

    @GetMapping("/products")
    public String getProducts() {
        return "product";
    }

    @GetMapping("/products/{productId}")
    public String product(@PathVariable Long productId , ModelMap model , HttpServletResponse response) throws IOException {
        Optional<Product> productOpt = productRepo.findById(productId);

        if(productOpt.isPresent()){
            Product product = productOpt.get();
            model.put("product", product);
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value() , "Product with id "+ productId +  " was not found" );
            return "product";
        }

        return "product";
    }

    @PostMapping("/products")
    public String createProduct(@AuthenticationPrincipal User user) {
        Product product = new Product();
        product.setPublished(false);
        product.setUser(user);
        product = productRepo.save(product);


        return "redirect:/products/"+product.getId() ;
    }

    @PostMapping("products/{productId}")
    public String saveProduct(@PathVariable Long productId , Product product){
        product = productRepo.save(product);
        return "redirect:/products/"+product.getId() ;
    }
}
