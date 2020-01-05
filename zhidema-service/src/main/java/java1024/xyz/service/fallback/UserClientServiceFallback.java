package java1024.xyz.service.fallback;

import feign.hystrix.FallbackFactory;
import java1024.xyz.service.UserClientService;
import org.springframework.stereotype.Component;

@Component
public class UserClientServiceFallback implements FallbackFactory<UserClientService> {
    @Override
    public UserClientService create(Throwable throwable) {
        return new UserClientService() {


        };
    }
}
