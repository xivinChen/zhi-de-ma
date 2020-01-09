package java1024.xyz.service;

import java1024.xyz.vo.Platform;
import java1024.xyz.vo.Result;

import java.util.List;

public interface PlatformService {

    Result<Integer> save(Platform platform);
    Result<Integer> update(Platform platform);
    Result<Platform> get(int id);
    Result<List<Platform>> findAll();
    Result<Platform> getBySign(String sign);

}
