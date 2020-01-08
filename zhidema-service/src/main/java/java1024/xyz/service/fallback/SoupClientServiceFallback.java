package java1024.xyz.service.fallback;

import feign.hystrix.FallbackFactory;
import java1024.xyz.service.SoupClientService;
import java1024.xyz.vo.Product;
import java1024.xyz.vo.Result;
import java1024.xyz.vo.Url;
import org.springframework.stereotype.Component;

/**
 * @author xivin
 * @email 1250402127@qq.com
 * @description
 * @date 2020/1/7
 */
@Component
public class SoupClientServiceFallback implements FallbackFactory<SoupClientService> {

    @Override
    public SoupClientService create(Throwable cause) {

        return new SoupClientService() {
            @Override
            public Result<Product> getOne(Url url) {

                Product product = new Product();
                product.setId(-1L);
                product.setTitle("hystrix");
                return new Result<>(product);
            }
        };
    }
}
