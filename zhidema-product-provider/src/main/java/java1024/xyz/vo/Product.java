package java1024.xyz.vo;

import lombok.Data;

import java.util.List;

/**
 * @author xivin
 * @email 1250402127@qq.com
 * @description
 * @date 2020/1/3
 */
@Data
public class Product {

    private Integer id;
    private Long number;
    private Integer platform;
    private String title;
    private Float price;
    private List<String> images;
    private String describe;

}
