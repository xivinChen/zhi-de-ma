package java1024.xyz.controller;

import java1024.xyz.service.ProductService;
import java1024.xyz.vo.Product;
import java1024.xyz.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xivin
 * @email 1250402127@qq.com
 * @description
 * @date 2020/1/3
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/search_and_save")
    public Result<Product> SearchAndSaveProduct(String url) {

        if (StringUtils.isEmpty(url)) {
            return new Result<>(301,"商品地址不能为空，请输入商品地址！",20002);
        }
        return productService.searchAndSave(url);
    }

    @PostMapping("/all")
    public Result<List<Product>> addAndGet(@RequestBody Product product) {
        Result saveResult = productService.addAndGet(product);
        return saveResult;
    }

    @PostMapping("/")
    public Result<Integer> save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/{id}")
    public Result<Product> get(@PathVariable("id") Long id) {
        return productService.get(id);
    }

    @GetMapping("/search")
    public Result<List<Product>> getByPlaformAndNumber(@RequestParam("platformId") Integer platformId,@RequestParam("number") Long number) {
        System.out.println("get product by platform and number");
        return productService.getByPlatformAndNumber(platformId, number);
    }
}
