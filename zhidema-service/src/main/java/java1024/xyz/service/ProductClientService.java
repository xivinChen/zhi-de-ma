package java1024.xyz.service;

import java1024.xyz.config.FeignClientConfig;
import java1024.xyz.service.fallback.ProductClientServiceFallback;
import java1024.xyz.vo.Product;
import java1024.xyz.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "zhidema-provider",configuration = FeignClientConfig.class,fallbackFactory = ProductClientServiceFallback.class)
public interface ProductClientService {

    String preUrl = "/product/";

    @PostMapping(preUrl+"all/")
    Result<List<Product>> all(@RequestBody Product product);

    @GetMapping(preUrl + "search/")
    Result<List<Product>> listByPlatfromAndNumber(@RequestParam("platformId") Integer platformId, @RequestParam("number") Long number);
}
