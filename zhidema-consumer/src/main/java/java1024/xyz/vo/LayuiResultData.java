package java1024.xyz.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author xivin
 * @email 1250402127@qq.com
 * @description
 * @date 2019/12/20
 */
public class LayuiResultData<T> implements Serializable {

    private int code;
    private int status;
    private String message;
    private long total;
    private long count;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
