package java1024.xyz.mapper;

import java1024.xyz.vo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> getByPlatfromAndNumber(@Param("platformId") int platformId, @Param("number") Long number);

    int checkExistByNumberAndPrice(@Param("number") Long number, @Param("price") Float price);
}