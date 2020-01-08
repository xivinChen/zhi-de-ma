package java1024.xyz.controller;

import java1024.xyz.service.SoupClientService;
import java1024.xyz.vo.Product;
import java1024.xyz.vo.Result;
import java1024.xyz.vo.Url;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xivin
 * @email 1250402127@qq.com
 * @description
 * @date 2020/1/7
 */
@RestController
@RequestMapping("/soup")
public class SoupClientController {

    @Resource
    private SoupClientService soupClientService;

    @PostMapping("/one")
    public Result<Product> getOne(Url url) {
        System.err.println("url = " + url);
        return soupClientService.getOne(url);
    }
}
