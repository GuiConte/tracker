package com.guiconte.tracker.rest.controller;

import com.guiconte.tracker.domain.dto.ProductQRCodeDTO;
import com.guiconte.tracker.model.ProductDTO;
import com.guiconte.tracker.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ui")
public class ProductUiController {

    private final ProductService productService;

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<ProductDTO> products = productService.findProducts();
        List<ProductQRCodeDTO> productsWithQRCode = productService.getProductQRCode(products);
        model.addAttribute("products", productsWithQRCode);
        return "product-list";
    }

    @GetMapping("/products/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "product-form";
    }

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute("product") ProductDTO productDTO) {
        productService.addProduct(productDTO);
        return "redirect:/ui/products";
    }

    @GetMapping("/product/{id}")
    public String productDetails(@PathVariable("id") UUID id, Model model) {
        ProductDTO product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "product-details";
    }
}
