package com.power.inventory.inventory.modules.user.dao;

import com.power.inventory.inventory.common.constant.SortEnum;
import com.power.inventory.inventory.modules.user.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    Long getTotalCounts(@Param("likeUser") User likeUser);

    List<User> findAll();

    List<User> findUser(@Param("likeUser") User likeUser, @Param("rowNum")Long rowNum, @Param("nRows")Long nRows);

    int editUser(User record);

    int delUserList(@Param("codeList") List<String> codeList);

    int updateUserStatus(@Param("code") String code, @Param("status") int status);
}