package java1024.xyz.controller;

import java1024.xyz.service.PlatformClientService;
import java1024.xyz.vo.Platform;
import java1024.xyz.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/platform")
public class PlatfromClientController {

    @Resource
    private PlatformClientService platformClientService;

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") int id) {
        return platformClientService.get(id);
    }

    @GetMapping("/")
    public Result findAll() {
        return platformClientService.findAll();
    }

    @GetMapping("/test")
    public Platform test() {
        return platformClientService.test();
    }
}
