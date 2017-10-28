package com.kaishengit.mapper;

import com.kaishengit.entity.User;
import com.kaishengit.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> page(@Param("pageSize")int pageSize,
                    @Param("pageNum")int pageNum);
    User findCountryByUserId(@Param("id") int id);
    User findUserTagById(int id);
    int batchInsert(@Param("userList") List<User> userList);
}