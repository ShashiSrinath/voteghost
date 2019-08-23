package com.voteghost.web;

import com.voteghost.domain.Feature;
import com.voteghost.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.UTF_8;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Optional;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {
    @Autowired
    private FeatureService featureService;


    @GetMapping("/new")
    public String newFeature(ModelMap model) {
        boolean newFeature = true;
        model.put("feature", new Feature());
        model.put("newFeature",newFeature);
        return "feature";
    }

    @PostMapping("/new")
    public String createFeature(Feature feature, @PathVariable Long productId) {
        feature = featureService.createFeature(productId, feature.getTitle(), feature.getDescription());
        return "redirect:/products/" + productId + "/features/" + feature.getId();
    }

    @GetMapping("/{featureId}")
    public String getFeature(@PathVariable Long productId, @PathVariable Long featureId, ModelMap model, HttpServletResponse response) throws IOException {
        Optional<Feature> featureOpt = featureService.loadFeature(featureId);
        if (featureOpt.isPresent()) {
            Feature feature = featureOpt.get();
            model.put("feature", feature);
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "feature with id " + featureId + " was not found");
        }
        return "feature";
    }

    @PostMapping("/{featureId}")
    public String updateFeature(Feature feature, @PathVariable Long featureId,ModelMap model, HttpServletResponse response) throws IOException {
        feature = featureService.updateFeature(feature,featureId);
        if (feature != null){
            model.put("feature", feature);
            return "redirect:/product/v/" + URLEncoder.encode(feature.getProduct().getName() , UTF_8.defaultCharset().name()) ;
        }else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "feature with id " + featureId + " was not found");
            return "feature";
        }

    }
}
