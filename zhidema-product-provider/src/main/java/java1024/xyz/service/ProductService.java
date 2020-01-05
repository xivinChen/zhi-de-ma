package java1024.xyz.service;

import java1024.xyz.vo.Product;
import java1024.xyz.vo.Result;

import java.util.List;

public interface ProductService {

    Result<Product> searchAndSave(String url);
    Result<Integer> save(Product product);
    Result<Integer> update(Product product);
    Result<List<Product>> getByPlatformAndNumber(Integer platformId ,Long number);

    Result<List<Product>> addAndGet(Product product);

    Result<Product> get(long id);
}
