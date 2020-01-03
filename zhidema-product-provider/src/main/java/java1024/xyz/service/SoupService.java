package java1024.xyz.service;

import java1024.xyz.vo.Product;

import java.util.List;

public interface SoupService {

    List<Product> soupTaobaoByKeyWord(String keyword);

    Product soupTaobaoDetailById(Long number);
}
