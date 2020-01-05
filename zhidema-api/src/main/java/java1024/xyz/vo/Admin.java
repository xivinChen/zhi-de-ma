package java1024.xyz.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Admin {
    private Integer id;

    private String account;

    private String password;

    private Integer status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Admin() {
        super();
    }


}