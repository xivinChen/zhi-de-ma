package java1024.xyz.service;

import java1024.xyz.vo.Result;
import java1024.xyz.vo.User;

public interface UserService {

    Result<Integer> save(User user);
    Result<Integer> update(User user);
    Result<User> get(int id);

}
