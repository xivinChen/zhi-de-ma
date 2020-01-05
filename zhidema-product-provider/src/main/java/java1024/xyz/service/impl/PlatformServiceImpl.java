package java1024.xyz.service.impl;

import java1024.xyz.mapper.PlatformMapper;
import java1024.xyz.service.PlatformService;
import java1024.xyz.vo.Platform;
import java1024.xyz.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlatformServiceImpl implements PlatformService {

    @Resource
    private PlatformMapper platformMapper;

    @Override
    public Result<Integer> save(Platform platform) {
        platform.setStatus(1);
        platform.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        platform.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return new Result<>(platformMapper.insertSelective(platform));
    }

    @Override
    public Result<Integer> update(Platform platform) {
        platform.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return new Result<>(platformMapper.updateByPrimaryKeySelective(platform));
    }

    @Override
    public Result<Platform> get(int id) {
        return new Result<>(platformMapper.selectByPrimaryKey(id));
    }

    @Override
    public Result<List<Platform>> findAll() {
        return new Result<>(platformMapper.findAll());
    }
}
