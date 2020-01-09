package java1024.xyz.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

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

    private String url;

    private Integer platformId;

    private String title;

    private String describe;

    private Integer status;

    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Platform platform;

}
