package java1024.xyz.controller;

import java1024.xyz.service.PlatformService;
import java1024.xyz.vo.Platform;
import java1024.xyz.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platform")
public class PlatfromController {

    @Autowired
    private PlatformService platformService;

    @GetMapping("/test")
    public Platform test() {
        Platform platform = new Platform();
        platform.setId(1);
        platform.setName("this is test");
        platform.setDetailUrl("https://java1024.xyz/deatil");
        platform.setSearchUrl("https://java1024.xyz/search?key=");
        platform.setSign("java1024.xyz");
        return platform;
    }

    @PostMapping("/")
    public Result<Integer> add(@RequestBody Platform platform) {
        return platformService.save(platform);
    }

    @PutMapping("/")
    public Result<Integer> update(@RequestBody Platform platform) {
        return platformService.update(platform);
    }

    @GetMapping("/{id}")
    public Result<Platform> get(@PathVariable("id") int id) {
        System.out.println("get one platform provider");
        Result result = platformService.get(id);
        return result;
    }

    @GetMapping("/")
    public Result<List<Platform>> findAll() {
        System.out.println("get all platform provide");
        Result result = platformService.findAll();
        return result;
    }
}
