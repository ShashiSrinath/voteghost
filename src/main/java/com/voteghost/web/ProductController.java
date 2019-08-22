package com.voteghost.web;

import com.voteghost.domain.Product;
import com.voteghost.domain.User;
import com.voteghost.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sun.nio.cs.UTF_8;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Optional;

@Controller
public class ProductController {
    private Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepo;


    @GetMapping("/products/{productId}")
    public String product(@PathVariable Long productId , ModelMap model , HttpServletResponse response) throws IOException {
        Optional<Product> productOpt = productRepo.findByIdWithUser(productId);

        if(productOpt.isPresent()){
            Product product = productOpt.get();
            model.put("product", product);
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value() , "Product with id "+ productId +  " was not found" );
            return "product";
        }

        return "product";
    }

    @GetMapping("product/v/{encodedProductName}")
    public String viewProduct(@PathVariable String encodedProductName,ModelMap model,HttpServletResponse response) throws IOException {
        try {
            String productName = URLDecoder.decode(encodedProductName , UTF_8.defaultCharset().name());
            Optional<Product> productOpt = productRepo.findByName(productName);

            if(productOpt.isPresent()){
                model.put("product",productOpt.get());
            } else {
                response.sendError(HttpStatus.NOT_FOUND.value() , "Product  not found" );
            }
        } catch (UnsupportedEncodingException e) {
            log.error("There Was A Error Decoding A Product Url",e);
        }

        return "productVisitorView";
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
