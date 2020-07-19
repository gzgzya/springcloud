package com.jinan.springcloud.mapper;

import com.jinan.springcloud.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {


     void saveBatch(List<User> users);


}
