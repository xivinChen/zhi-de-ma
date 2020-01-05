/**
 * FileName: FeignClientConfig
 * Author: xivin
 * Date: 2019-09-04 11:11
 * Description:
 */
package java1024.xyz.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    public static int connectTimeOutMillis = 5000;//超时时间
    public static int readTimeOutMillis = 5000;
    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default();
    }
}
