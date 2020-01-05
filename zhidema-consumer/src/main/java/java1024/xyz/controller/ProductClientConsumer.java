package java1024.xyz.controller;

import java1024.xyz.service.ProductClientService;
import java1024.xyz.vo.Product;
import java1024.xyz.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
public class ProductClientConsumer {

    @Resource
    private ProductClientService productClientService;

    @PostMapping("/all")
    public Result all(@RequestBody Product product) {
        return productClientService.all(product);
    }

    @GetMapping("/search")
    public Result search(Integer platformId, Long number) {
        return productClientService.listByPlatfromAndNumber(platformId,number);
    }
}
