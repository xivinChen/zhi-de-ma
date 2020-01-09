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

            // 判空
            if (StringUtils.isEmpty(url)) {
                urlData.setStatus(0);
                return urlData;
            }

            //天猫
            if (url.contains(UrlConst.tmallUrlSign)) {

                urlData.setPlatform(UrlConst.tmallUrlSign);
                String numberStr = "";

                /**
                 * 切分根路径 和 参数 如：
                 * https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.8.27832a99AfoD5W&id=604433373792
                 * 在 ？问号的地方切成两部分
                 *
                 */
                String[] roudAndParams = url.split("\\?");

                if (roudAndParams.length < 2) {
                    urlData.setStatus(0);
                    return urlData;
                }

                /**
                 * 获取 参数字符串，通过&切开多个参数，提取以 id=开头的即 商品id
                 */
                String paramStr =  roudAndParams[1];
                String[] params = paramStr.split("&");
                for (int i = 0;i < params.length; i++) {
                    if (params[i].startsWith("id=")) {
                        numberStr = params[i].split("id=")[1];
                        break;
                    }
                }

                if (StringUtils.isEmpty(numberStr)) {
                    urlData.setStatus(0);
                    return urlData;
                }

                Long number = new Long(numberStr);
                urlData.setStatus(1);
                urlData.setNumber(number);
                return urlData;

            }
            //淘宝
            else if (url.contains(UrlConst.taobaoUrlSign)) {

                urlData.setPlatform(UrlConst.taobaoUrlSign);
                String numberStr = "";

                /**
                 * 切分根路径 和 参数 如：
                 * https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.8.27832a99AfoD5W&id=604433373792
                 * 在 ？问号的地方切成两部分
                 *
                 */
                String[] roudAndParams = url.split("\\?");

                if (roudAndParams.length < 2) {
                    urlData.setStatus(0);
                    return urlData;
                }

                /**
                 * 获取 参数字符串，通过&切开多个参数，提取以 id=开头的即 商品id
                 */
                String paramStr =  roudAndParams[1];
                String[] params = paramStr.split("&");
                for (int i = 0;i < params.length; i++) {
                    if (params[i].startsWith("id=")) {
                        numberStr = params[i].split("id=")[1];
                        break;
                    }
                }

                if (StringUtils.isEmpty(numberStr)) {
                    urlData.setStatus(0);
                    return urlData;
                }

                Long number = new Long(numberStr);
                urlData.setStatus(1);
                urlData.setNumber(number);
                return urlData;
            }
            //其他
            else if (url.contains(UrlConst.jingdongUrlSign)) {

                urlData.setPlatform(UrlConst.jingdongUrlSign);
                String numberStr = "";
                String[] roudAndParams = url.split("jd\\.com/");

                if (roudAndParams.length < 2) {
                    urlData.setStatus(0);
                    return urlData;
                }

                String paramStr =  roudAndParams[1];
                String[] params = paramStr.split(".html");
                numberStr = params[0];

                if (StringUtils.isEmpty(numberStr)) {
                    urlData.setStatus(0);
                    return urlData;
                }

                Long number = new Long(numberStr);
                urlData.setStatus(1);
                urlData.setNumber(number);
                return urlData;
            }
            else {
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

        String tmallUrl = "https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.8.27832a99AfoD5W&id=604433373792&skuId=4233630160968&user_id=1776477331&cat_id=2&is_b=1&rn=2eff85a6a504024ee62222a0045d9ded";
        UrlData tmall = analyseUrl(tmallUrl);
        System.out.println("tmall = " + tmall);

        String taobaoUrl =  "https://s.taobao.com/search?spm=a230r.1.14.7.ade0695abTrJ6k&type=samestyle&app=i2i&rec_type=1&uniqpid=69915374&nid=604733501729";
        UrlData taobao = analyseUrl(taobaoUrl);
        System.out.println("taobao = " + taobao);

        String jdUrl = "https://item.jd.com/100004250098.html#none";
        UrlData jd = analyseUrl(jdUrl);
        System.out.println("jd = " + jd);

    }

}
