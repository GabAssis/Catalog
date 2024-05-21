package com.gabriel.dscatalog.controllers;

import com.gabriel.dscatalog.models.Product;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ProductController {




}
