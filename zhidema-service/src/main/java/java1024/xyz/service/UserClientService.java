package java1024.xyz.service;

import java1024.xyz.config.FeignClientConfig;
import java1024.xyz.service.fallback.UserClientServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "zhidema-provider",configuration = FeignClientConfig.class,fallbackFactory = UserClientServiceFallback.class)
public interface UserClientService {

}
