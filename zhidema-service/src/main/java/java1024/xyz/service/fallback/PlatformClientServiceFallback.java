package java1024.xyz.service.fallback;

import feign.hystrix.FallbackFactory;
import java1024.xyz.service.PlatformClientService;
import java1024.xyz.vo.Platform;
import java1024.xyz.vo.Product;
import java1024.xyz.vo.Result;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlatformClientServiceFallback implements FallbackFactory<PlatformClientService> {
    @Override
    public PlatformClientService create(Throwable throwable) {
        return new PlatformClientService() {
            @Override
            public Result get(int id) {
                Platform platform = new Platform();
                platform.setId(-1);
                platform.setName("hystrix");

                return new Result<>(platform);
            }

            @Override
            public Result findAll() {
                Platform platform = new Platform();
                platform.setId(-1);
                platform.setName("hystrix");
                List list = new ArrayList();
                list.add(platform);
                return new Result<>(list);
            }

            @Override
            public Result<Integer> add(Platform platform) {
                return new Result<>(-1);
            }

            @Override
            public Result<Integer> update(Platform platform) {
                return new Result<>(-1);
            }

            @Override
            public Platform test() {

                Platform platform = new Platform();
                platform.setId(-1);
                platform.setName("hystrix");
                return platform;
            }
        };
    }
}
