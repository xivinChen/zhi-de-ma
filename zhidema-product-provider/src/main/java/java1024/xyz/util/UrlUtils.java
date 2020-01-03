package java1024.xyz.util;

import java1024.xyz.vo.UrlConst;
import java1024.xyz.vo.UrlData;
import org.springframework.util.StringUtils;

/**
 * @author xivin
 * @email 1250402127@qq.com
 * @description
 * @date 2020/1/3
 */
public class UrlUtils {

    public static UrlData analyseUrl(String url) {

        UrlData urlData = new UrlData();
        try {

            if (StringUtils.isEmpty(url)) {
                urlData.setStatus(0);
                return urlData;
            }

            if (url.contains(UrlConst.tmallUrlSign)) {

                urlData.setPlatform(UrlConst.tmallUrlSign);
                String numberStr = "";
                if (url.contains("?id=")) {
                    int index = url.indexOf("?id=");
                    numberStr = url.substring(index + 4, index + 16);
                } else if (url.contains("&id=")) {
                    int index = url.indexOf("&id=");
                    numberStr = url.substring(index + 4, index + 16);
                } else {
                    urlData.setStatus(0);
                    return urlData;
                }

                if (StringUtils.isEmpty(numberStr)) {

                    urlData.setStatus(0);
                    return urlData;
                }

                Long number = new Long(numberStr);
                urlData.setStatus(1);
                urlData.setNumber(number);
                return urlData;

            } else {
                urlData.setStatus(0);
                return urlData;
            }
        }catch (Exception e) {
            e.printStackTrace();
            urlData.setStatus(0);
            return urlData;
        }

    }

    public static void main(String[] args) {
        String testUrl = "https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.8.27832a99AfoD5W&id=604433373792&skuId=4233630160968&user_id=1776477331&cat_id=2&is_b=1&rn=2eff85a6a504024ee62222a0045d9ded";
        String testUrl2 =  "https://detail.tmall.com/item.htm?id=604433373792";
        UrlData urlData = analyseUrl(testUrl2);
        System.out.println("urlData = " + urlData);

        String testUrlEr1 =  "https://detail.tmall.com/item.htm?id=604433373792";
        UrlData urlData1 = analyseUrl(testUrlEr1);
        System.out.println("urlData1 = " + urlData1);
    }

}
