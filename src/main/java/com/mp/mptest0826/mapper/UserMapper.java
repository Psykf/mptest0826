package com.mp.mptest0826.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.mptest0826.entity.User;
import org.springframework.stereotype.Repository;

@Repository //交给spring管理，一启动就扫描创建
public interface UserMapper extends BaseMapper<User> {

}
