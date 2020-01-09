package java1024.xyz.service.impl;

import java1024.xyz.service.PlatformService;
import java1024.xyz.service.SoupService;
import java1024.xyz.vo.Platform;
import java1024.xyz.vo.Product;
import java1024.xyz.vo.Result;
import java1024.xyz.vo.UrlConst;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xivin
 * @email 1250402127@qq.com
 * @description
 * @date 2020/1/3
 */
@Service
public class SoupServiceImpl implements SoupService {

    @Resource
    private PlatformService platformService;


    public List<Product> soupTaobaoByKeyWord(String keyword) {

        try {

            String input = "毛巾";
            // 需要爬取商品信息的网站地址
            String url = "https://list.tmall.com/search_product.htm?q=" + input;
            // 动态模拟请求数据
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            // 模拟浏览器浏览（user-agent的值可以通过浏览器浏览，查看发出请求的头文件获取）
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
            CloseableHttpResponse response = httpclient.execute(httpGet);
            // 获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            try {
                HttpEntity entity = response.getEntity();
                // 如果状态响应码为200，则获取html实体内容或者json文件
                if (statusCode == 200) {
                    String html = EntityUtils.toString(entity, Consts.UTF_8);
                    // 提取HTML得到商品信息结果
                    Document doc = null;
                    // doc获取整个页面的所有数据
                    doc = Jsoup.parse(html);
                    //输出doc可以看到所获取到的页面源代码
            //      System.out.println(doc);
                    // 通过浏览器查看商品页面的源代码，找到信息所在的div标签，再对其进行一步一步地解析
                    Elements ulList = doc.select("div[class='view grid-nosku']");
                    Elements liList = ulList.select("div[class='product']");
                    // 循环liList的数据（具体获取的数据值还得看doc的页面源代码来获取，可能稍有变动）
                    for (Element item : liList) {
                        // 商品ID
                        String id = item.select("div[class='product']").select("p[class='productStatus']").select("span[class='ww-light ww-small m_wangwang J_WangWang']").attr("data-item");
                        System.out.println("商品ID：" + id);
                        // 商品名称
                        String name = item.select("p[class='productTitle']").select("a").attr("title");
                        System.out.println("商品名称：" + name);
                        // 商品价格
                        String price = item.select("p[class='productPrice']").select("em").attr("title");
                        System.out.println("商品价格：" + price);
                        // 商品网址
                        String goodsUrl = item.select("p[class='productTitle']").select("a").attr("href");
                        System.out.println("商品网址：" + goodsUrl);
                        // 商品图片网址
                        String imgUrl = item.select("div[class='productImg-wrap']").select("a").select("img").attr("data-ks-lazyload");
                        System.out.println("商品图片网址：" + imgUrl);
                        System.out.println("------------------------------------");
                    }
                    // 消耗掉实体
                    EntityUtils.consume(response.getEntity());
                } else {
                    // 消耗掉实体
                    EntityUtils.consume(response.getEntity());
                }
            } finally {
                response.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public Product soupTmallDetailById(Long number) {

        try {


            // 需要爬取商品信息的网站地址
            String url = "https://chaoshi.detail.tmall.com/item.htm?id=" + number;
            // 动态模拟请求数据
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            // 模拟浏览器浏览（user-agent的值可以通过浏览器浏览，查看发出请求的头文件获取）
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
            CloseableHttpResponse response = httpclient.execute(httpGet);
            // 获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            try {
                HttpEntity entity = response.getEntity();
                // 如果状态响应码为200，则获取html实体内容或者json文件
                if (statusCode == 200) {
                    String html = EntityUtils.toString(entity, Consts.UTF_8);
                    // 提取HTML得到商品信息结果
                    Document doc = null;
                    // doc获取整个页面的所有数据
                    doc = Jsoup.parse(html);
                    //输出doc可以看到所获取到的页面源代码
                  //System.out.println(doc);
                    // 通过浏览器查看商品页面的源代码，找到信息所在的div标签，再对其进行一步一步地解析
                    Element item = doc.select("div[class='tb-wrap']").get(0);
                    //Elements liList = ulList.select("div[class='product']");
                    // 循环liList的数据（具体获取的数据值还得看doc的页面源代码来获取，可能稍有变动）
                    //System.out.println("item = " + item);
                    Product product = new Product();
                    //for (Element item : ulList) {
                        // 商品ID
                    try {
                        Result<Platform> bySign = platformService.getBySign(UrlConst.tmallUrlSign);
                        Platform platform = bySign.getData();
                        if (platform != null) {
                            product.setPlatformId(platform.getId());
                            product.setPlatform(platform);
                        }
                        product.setNumber(number);
                        product.setPlatformId(1);
                        //String id = item.select("div[class='tb-detail-hd']").select("h1").attr("data-spm");
                        String title = item.select("div[class='tb-detail-hd']").select("h1").text();
                        product.setTitle(title);
                        product.setUrl(UrlConst.TMALL_PRODUCT_DETAIL+number);

                        System.out.println("商品title：" + title);
                        //String priceStr = item.select("div[class='tm-price-panel']").select("div[class='tm-promo-type']").select("span[class='tm-price']").text();

                        return product;
                    }catch (Exception e) {
                        product.setId(0L);
                        product.setTitle("商品不存在");
                        return product;
                    }
                    // }
                }
            }catch (Exception e) {
                e.printStackTrace();
                Product product = new Product();
                product.setId(0L);
                product.setTitle("商品不存在");
                return product;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Product soupTaobaoDetailById(Long number) {

        try {

            // 需要爬取商品信息的网站地址
            String url = "https://item.taobao.com/item.htm?id=" + number;
            // 动态模拟请求数据
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            // 模拟浏览器浏览（user-agent的值可以通过浏览器浏览，查看发出请求的头文件获取）
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
            CloseableHttpResponse response = httpclient.execute(httpGet);
            // 获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            try {
                HttpEntity entity = response.getEntity();
                // 如果状态响应码为200，则获取html实体内容或者json文件
                if (statusCode == 200) {
                    String html = EntityUtils.toString(entity, Consts.UTF_8);
                    // 提取HTML得到商品信息结果
                    Document doc = null;
                    // doc获取整个页面的所有数据
                    doc = Jsoup.parse(html);
                    //输出doc可以看到所获取到的页面源代码
                    //System.out.println(doc);
                    // 通过浏览器查看商品页面的源代码，找到信息所在的div标签，再对其进行一步一步地解析
                    Element item = doc.select("div[class='tb-item-info-r']").get(0);
                    //Elements liList = ulList.select("div[class='product']");
                    // 循环liList的数据（具体获取的数据值还得看doc的页面源代码来获取，可能稍有变动）
                    //System.out.println("item = " + item);
                    Product product = new Product();
                    //for (Element item : ulList) {
                    // 商品ID
                    try {
                        Result<Platform> bySign = platformService.getBySign(UrlConst.taobaoUrlSign);
                        Platform platform = bySign.getData();
                        if (platform != null) {
                            product.setPlatformId(platform.getId());
                            product.setPlatform(platform);
                        }
                        product.setNumber(number);
                        product.setPlatformId(2);
                        //String id = item.select("div[class='tb-detail-hd']").select("h1").attr("data-spm");
                        String title = item.select("div[class='tb-title']").select("h3").text();
                        product.setTitle(title);
                        product.setUrl(UrlConst.TAOBAO_PRODUCT_DETAIL+number);

                        System.out.println("商品title：" + title);

                        return product;
                    }catch (Exception e) {
                        product.setId(0L);
                        product.setTitle("商品不存在");
                        return product;
                    }
                    // }
                }
            }catch (Exception e) {
                e.printStackTrace();
                Product product = new Product();
                product.setId(0L);
                product.setTitle("商品不存在");
                return product;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Product soupJDDetailById(Long number) {
        try {

            // 需要爬取商品信息的网站地址
            String url = "https://item.jd.com/"+number+".html";
            // 动态模拟请求数据
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            // 模拟浏览器浏览（user-agent的值可以通过浏览器浏览，查看发出请求的头文件获取）
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
            CloseableHttpResponse response = httpclient.execute(httpGet);
            // 获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            try {
                HttpEntity entity = response.getEntity();
                // 如果状态响应码为200，则获取html实体内容或者json文件
                if (statusCode == 200) {
                    String html = EntityUtils.toString(entity, Consts.UTF_8);
                    // 提取HTML得到商品信息结果
                    Document doc = null;
                    // doc获取整个页面的所有数据
                    doc = Jsoup.parse(html);
                    //输出doc可以看到所获取到的页面源代码
                    //System.out.println(doc);
                    // 通过浏览器查看商品页面的源代码，找到信息所在的div标签，再对其进行一步一步地解析
                    Element item = doc.select("div[class='itemInfo-wrap']").get(0);
                    //Elements liList = ulList.select("div[class='product']");
                    // 循环liList的数据（具体获取的数据值还得看doc的页面源代码来获取，可能稍有变动）
                    //System.out.println("item = " + item);
                    Product product = new Product();
                    //for (Element item : ulList) {
                    // 商品ID
                    try {
                        Result<Platform> bySign = platformService.getBySign(UrlConst.jingdongUrlSign);
                        Platform platform = bySign.getData();
                        if (platform != null) {
                            product.setPlatformId(platform.getId());
                            product.setPlatform(platform);
                        }
                        product.setNumber(number);
                        product.setPlatformId(3);
                        //String id = item.select("div[class='tb-detail-hd']").select("h1").attr("data-spm");
                        String title = item.select("div[class='sku-name']").text();
                        product.setTitle(title);
                        product.setUrl(UrlConst.JD_PRODUCT_DETAIL+number+".html");

                        System.out.println("商品title：" + title);

                        return product;
                    }catch (Exception e) {
                        product.setId(0L);
                        product.setTitle("商品不存在");
                        return product;
                    }
                    // }
                }
            }catch (Exception e) {

                e.printStackTrace();
                Product product = new Product();
                product.setId(0L);
                product.setTitle("商品不存在");
                return product;

            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public  void getPrice() {
        try {
            String url = "http://mdskip.taobao.com/core/initItemDetail.htm?isRegionLevel=true&itemTags=385,775,843,1035,1163,1227,1478,1483,1539,1611,1863,1867,1923,2049,2059,2242,2251,2315,2507,2635,3595,3974,4166,4299,4555,4811,5259,5323,5515,6145,6785,7809,9153,11265,12353,12609,13697,13953,16321,16513,17473,17537,17665,17857,18945,19841,20289,21762,21826,25922,28802,53954&tgTag=false&addressLevel=4&isAreaSell=false&sellerPreview=false&offlineShop=false&showShopProm=false&isIFC=false&service3C=true&isSecKill=false&isForbidBuyItem=false&cartEnable=true&sellerUserTag=839979040&queryMemberRight=true&itemId=40533381395&sellerUserTag2=306250462070310924&household=false&isApparel=false¬AllowOriginPrice=false&tmallBuySupport=true&sellerUserTag3=144467169269284992&sellerUserTag4=1152930305168967075&progressiveSupport=true&isUseInventoryCenter=false&tryBeforeBuy=false&callback=setMdskip×tamp=1420351892310";

            HttpClientBuilder builder = HttpClients.custom();
            builder.setUserAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)");
            CloseableHttpClient httpClient = builder.build();
            final HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Referer", "http://detail.tmall.com/item.htm?id=40533381395&skuId=68347779144&areaId=110000&cat_id=50024400&rn=763d147479ecdc17c2632a4219ce96b3&standard=1&user_id=263726286&is_b=1");
            CloseableHttpResponse response = null;
            response = httpClient.execute(httpGet);
            final HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            }

            //商品价格的返回值，需要解析出来价格

            result = result.substring(10, result.length() - 1);
            System.out.println("result = " + result);

            response.close();
            httpClient.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void soupJdDetail(Long number) {
        try {


            // 需要爬取商品信息的网站地址
            String url = "https://item.jd.com/" + number+".html";
            // 动态模拟请求数据
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            // 模拟浏览器浏览（user-agent的值可以通过浏览器浏览，查看发出请求的头文件获取）
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
            CloseableHttpResponse response = httpclient.execute(httpGet);
            // 获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            try {
                HttpEntity entity = response.getEntity();
                // 如果状态响应码为200，则获取html实体内容或者json文件
                if (statusCode == 200) {
                    String html = EntityUtils.toString(entity, Consts.UTF_8);
                    // 提取HTML得到商品信息结果
                    Document doc = null;
                    // doc获取整个页面的所有数据
                    doc = Jsoup.parse(html);
                    //输出doc可以看到所获取到的页面源代码
                    System.out.println(doc);
                    // 通过浏览器查看商品页面的源代码，找到信息所在的div标签，再对其进行一步一步地解析
                    Element item = doc.select("div[class='tb-wrap']").get(0);

                }
            } catch (Exception e) {

            }
        }catch (Exception e) {

        }
    }


}
