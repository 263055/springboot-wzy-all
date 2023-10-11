package com.example.loser.biz;

import com.example.loser.core.Param;
import com.example.loser.core.Select;

public interface SysUserDao {

    @Select("select * from sys_user where id = #{id}")
    SysUser getById(@Param("id") Long id);

}
