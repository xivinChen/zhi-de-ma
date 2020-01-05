package java1024.xyz.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author xivin
 * @email 1250402127@qq.com
 * @description
 * @date 2020/1/3
 */
@Data
public class Product implements Serializable {

    private Long id;

    private Long number;

    private Float price;

    private Integer userId;

    private Integer platformId;

    private String title;

    private String describe;

    private Integer status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
