package com.yinuo.user.mapper;



import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

import com.inuol.user.User;

public interface UserMapper extends Mapper<User> {

    public List<Map<String,String>> authList(Long id);

}
