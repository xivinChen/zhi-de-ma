package java1024.xyz.service.impl;

import java1024.xyz.mapper.ProductMapper;
import java1024.xyz.service.ProductService;
import java1024.xyz.service.SoupService;
import java1024.xyz.util.UrlUtils;
import java1024.xyz.vo.Product;
import java1024.xyz.vo.Result;
import java1024.xyz.vo.UrlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SoupService soupService;

    @Resource
    private ProductMapper productMapper;

    @Override
    public Result<Product> searchAndSave(String url) {
        UrlData urlData = UrlUtils.analyseUrl(url);
        if (urlData.getStatus() != 1) {

            return new Result<>(404,"商品地址不合法，请输入合法的商品地址！正确如:https://detail.tmall.com/item.htm?id=604433373792l ，您的地址："+url,20002);
        }

        Product product = soupService.soupTaobaoDetailById(urlData.getNumber());
        return new Result<>(product);
    }

    @Override
    public Result<Integer> save(Product product) {
        product.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        product.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        product.setStatus(1);
        return new Result<>(productMapper.insertSelective(product));
    }

    @Override
    public Result<Integer> update(Product product) {
        return new Result<>(productMapper.updateByPrimaryKeySelective(product));
    }

    @Override
    public Result<List<Product>> getByPlatformAndNumber(Integer platformId, Long number) {
        return new Result<>(productMapper.getByPlatfromAndNumber(platformId, number));
    }

    @Override
    public Result<List<Product>> addAndGet(Product product) {
        int exist = productMapper.checkExistByNumberAndPrice(product.getNumber(),product.getPrice());

        this.save(product);

        return this.getByPlatformAndNumber(product.getPlatformId(), product.getNumber());
    }

    @Override
    public Result<Product> get(long id) {
        return new Result<>(productMapper.selectByPrimaryKey(id));
    }
}
