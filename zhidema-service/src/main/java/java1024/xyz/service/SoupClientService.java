package java1024.xyz.service;

import java1024.xyz.config.FeignClientConfig;
import java1024.xyz.service.fallback.SoupClientServiceFallback;
import java1024.xyz.vo.Product;
import java1024.xyz.vo.Result;
import java1024.xyz.vo.Url;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "zhidema-provider",configuration = FeignClientConfig.class,fallbackFactory = SoupClientServiceFallback.class)
public interface SoupClientService {

    @PostMapping("/soup/one")
    Result<Product> getOne(@RequestBody Url url);
}
