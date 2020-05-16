package com.example.mongodb.controller;

import com.example.mongodb.command.ProductCommand;
import com.example.mongodb.converter.ProductToProductCommand;
import com.example.mongodb.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

@Slf4j
@Controller
public class ProductController {

    private final ProductServiceImpl productService;
    private final ProductToProductCommand productToProductCommand;

    public ProductController(ProductServiceImpl productService, ProductToProductCommand productToProductCommand) {
        this.productService = productService;
        this.productToProductCommand = productToProductCommand;
    }

    @GetMapping("/")
    public String redirectToList(){

        return "redirect:/products/list";
    }

    @GetMapping({"/products", "/products/list"})
    public String listProducts(Model model){

        Set<ProductCommand> productSet = new HashSet<>();
        productService.findAll().forEach(product -> {
            productSet.add(productToProductCommand.convert(product));
        });
        model.addAttribute("products", productSet);

        return "/product/list";
    }

    @GetMapping("/product/show/{productId}")
    public String getProduct(@PathVariable String productId, Model model){
        model.addAttribute("product", productService.findById(new ObjectId(productId)).get());
        return "/product/show";
    }

    @GetMapping("/product/edit/{productId}")
    public String edit(@PathVariable String productId, Model model){

        Optional<ProductCommand> optionalProductCommand = productService.findById(new ObjectId(productId));
        model.addAttribute("productForm", optionalProductCommand.get());

        return "/product/productform";
    }

    @GetMapping("/product/new")
    public String newProductForm(Model model){
        model.addAttribute("productForm", new ProductCommand());
        return "/product/productform";
    }

    @PostMapping("/product")
    public String saveOrUpdate(@Valid ProductCommand productCommand, BindingResult bindingResult){

        // BindingResult holds validation result
        if(bindingResult.hasErrors()){
            return "/product/productform";
        }
        ProductCommand savedProductCommand = productService.save(productCommand);

        return "redirect:/product/show/" + savedProductCommand.getId();
    }

    @GetMapping("/product/delete/{productId}")
    public String deleteProduct(@PathVariable String productId){

        productService.deleteById(productId);
        return "redirect:/products/list";
    }
    
}
