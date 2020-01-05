package java1024.xyz.vo;

import lombok.Data;

@Data
public class Result<T> {

    private Integer status = 200;
    private String msg ="ok";
    private Integer code = 0;
    private T data;

    public Result(Integer status, String msg, Integer code) {
        this.status = status;
        this.msg = msg;
        this.code = code;
    }

    public Result() {
        super();
    }

    public Result(T data) {
        this.data = data;
    }
}
