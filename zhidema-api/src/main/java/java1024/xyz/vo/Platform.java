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
public class Platform implements Serializable {
    private Integer id;

    private String name;

    private String detailUrl;

    private String searchUrl;

    private String sign;

    private Integer status;

    private Timestamp createdAt;

    private Timestamp updatedAt;


    public Platform() {
        super();
    }


}