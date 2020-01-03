package java1024.xyz;

import java1024.xyz.service.impl.SoupServiceImpl;
import org.junit.Test;

/**
 * @author xivin
 * @email 1250402127@qq.com
 * @description
 * @date 2020/1/3
 */
public class SoupServiceTest {

    @Test
    public void testSoupTaobao() {
        SoupServiceImpl soupService = new SoupServiceImpl();
        soupService.soupTaobao();
    }

    @Test
    public void testSoupTaoBaoDetail() {
        SoupServiceImpl soupService = new SoupServiceImpl();
        soupService.soupTaobaoDetail();
    }
}
