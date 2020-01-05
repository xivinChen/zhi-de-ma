package java1024.xyz.mapper;

import java1024.xyz.vo.Platform;

import java.util.List;

public interface PlatformMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Platform record);

    int insertSelective(Platform record);

    Platform selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Platform record);

    int updateByPrimaryKey(Platform record);

    List<Platform> findAll();
}