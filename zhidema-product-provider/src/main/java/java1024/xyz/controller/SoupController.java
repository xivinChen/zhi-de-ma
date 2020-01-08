package java1024.xyz.controller;

import java1024.xyz.Conts.ResultStatus;
import java1024.xyz.service.SoupService;
import java1024.xyz.util.UrlUtils;
import java1024.xyz.vo.*;
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
public class SoupController {

    @Resource
    private SoupService soupService;

    @PostMapping("/one")
    public Result<Product> getOne(@RequestBody Url url) {
        String urlStr = url.getUrl();
        UrlData urlData = UrlUtils.analyseUrl(urlStr);

        if (urlData.getStatus() != 1) {
            Result result = new Result<>();
            result.setStatus(ResultStatus.ABNORMAL);
            result.setMsg("商品地址不正确，请输入正确的商品地址！");
            return result;
        }
        //天猫平台
        if (UrlConst.tmallUrlSign.equals(urlData.getPlatform())) {
            return new Result<>(soupService.soupTmallDetailById(urlData.getNumber()));
        }
        //淘宝平台
        else if (UrlConst.taobaoUrlSign.equals(urlData.getPlatform())) {
            return new Result<>(soupService.soupTaobaoDetailById(urlData.getNumber()));
        }
        else if (UrlConst.jingdongUrlSign.equals(urlData.getPlatform())) {
            return new Result<>(soupService.soupJDDetailById(urlData.getNumber()));
        }
        //其他平台
        else {
            Result result = new Result<>();
            result.setStatus(ResultStatus.ABNORMAL);
            result.setMsg("暂时不支持自动获取 该平台的商品信息，请手动录入商品信息！");
            return result;
        }
    }
}
