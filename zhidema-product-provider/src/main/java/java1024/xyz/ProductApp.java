package java1024.xyz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("java1024.xyz.mapper")
public class ProductApp
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
