package java1024.xyz.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String tel;

    private Integer status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public User() {
        super();
    }


}