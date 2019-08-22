package com.voteghost.service;

import com.voteghost.domain.Feature;
import com.voteghost.domain.Product;
import com.voteghost.repositories.FeatureRepository;
import com.voteghost.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureService {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private FeatureRepository featureRepo;

    public Feature createFeature(Long productId){
        Feature feature = new Feature();
        Optional<Product> productOpt = productRepo.findById(productId);
        if (productOpt.isPresent()){
            Product product = productOpt.get();

            feature.setProduct(product);
            product.getFeatures().add(feature);

            feature.setStatus("Pending Review");

            return featureRepo.save(feature);
        }

        return feature;

    }
}
