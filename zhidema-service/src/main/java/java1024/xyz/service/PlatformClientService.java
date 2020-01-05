package java1024.xyz.service;

import java1024.xyz.config.FeignClientConfig;
import java1024.xyz.service.fallback.PlatformClientServiceFallback;
import java1024.xyz.vo.Platform;
import java1024.xyz.vo.Product;
import java1024.xyz.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "zhidema-provider",fallbackFactory = PlatformClientServiceFallback.class)
public interface PlatformClientService {

    String preUrl = "/platform";

    @GetMapping(preUrl+"/test")
    Platform test();

    @GetMapping(preUrl+"/{id}")
    Result get(@PathVariable("id") int id);

    @GetMapping(preUrl+"/")
    Result findAll();

    @PostMapping(preUrl+"/")
    Result<Integer> add(@RequestBody Platform platform);

    @PutMapping(preUrl+"/")
    Result<Integer> update(@RequestBody Platform platform);

}
