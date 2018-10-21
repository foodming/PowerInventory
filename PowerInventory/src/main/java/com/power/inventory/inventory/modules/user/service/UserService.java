package com.power.inventory.inventory.modules.user.service;

import com.power.inventory.inventory.common.constant.SortEnum;
import com.power.inventory.inventory.modules.user.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserService {

    //@Transactional
    int addUser(User user);

    void delUser(long id);

//    List<User> findAllUser(int pageNum, int pageSize);
    List<User> findAllUser();

    Long getTotalCounts(User likeUser);

    List<User> findUser(Long currentPage, Long pageSize, User likeUser, String sortColumnName, SortEnum sortType);

    int editUser(User record);

    int deleteUser(List<String> codeList);

    int updateUserStatus(String code, int status);
}
