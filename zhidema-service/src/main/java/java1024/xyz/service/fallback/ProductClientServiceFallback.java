package java1024.xyz.service.fallback;

import feign.hystrix.FallbackFactory;
import java1024.xyz.service.ProductClientService;
import java1024.xyz.vo.Product;
import java1024.xyz.vo.Result;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductClientServiceFallback implements FallbackFactory<ProductClientService> {

    @Override
    public ProductClientService create(Throwable throwable) {
        return new ProductClientService() {
            @Override
            public Result<List<Product>> all(Product product) {
                Product product1 = new Product();
                product1.setId(-1L);
                product1.setTitle("hystrix");
                List list = new ArrayList();
                list.add(product1);
                return new Result<>(list);
            }

            @Override
            public Result<List<Product>> listByPlatfromAndNumber(Integer platformId, Long number) {

                Product product = new Product();
                product.setId(-1L);
                product.setTitle("hystrix");
                List list = new ArrayList();
                list.add(product);
                return new Result<>(list);
            }
        };
    }
}
