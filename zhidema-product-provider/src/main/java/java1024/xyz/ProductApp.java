package java1024.xyz;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("java1024.xyz.mapper")
@EnableDiscoveryClient
public class ProductApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProductApp.class,args);
    }
}
