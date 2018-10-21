package com.power.inventory.inventory.modules.user.service;

import com.power.inventory.inventory.common.constant.SortEnum;
import com.power.inventory.inventory.modules.user.dao.UserMapper;
import com.power.inventory.inventory.modules.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响

    public List<User> findAllUser() {

        List<User> users = userMapper.findAll();
        return users;
    }

    @Transactional(rollbackFor = {Exception.class})
    //@Transactional
    public int addUser(User user){

        int rn = 0;

        try {
            rn = userMapper.insert(user);

        }catch (Exception e){
            throw e;

        }finally {

        }

        return rn;
    }

    @Transactional(rollbackFor = {Exception.class})
    public void delUser(long id){

        userMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    public List<User> findUser(Long pageNum, Long pageSize, User likeUser, String sortColumn, SortEnum sortEnum){

        try {
//            String sqlWhere = where;
            String limit = String.format("limit %d,%d", pageSize * (pageNum - 1), pageSize);
            Long rowNum = pageSize * (pageNum - 1);
            Long nRows = pageSize;
            User user = likeUser;
            if(user == null){
                user = new User();
            }

            List<User> users = userMapper.findUser(user, rowNum, nRows);
            return users;
        }catch(Exception e){

            }

        return null;

    }

    public Long getTotalCounts(User likeUser){
        Long counts = 0L;
        try {
            counts = userMapper.getTotalCounts(likeUser);

        }catch(Exception e){

        }

        return counts;
    }

    @Transactional(rollbackFor = {Exception.class})
    public int editUser(User record){
        int rn = 0;
        try {
            rn = userMapper.editUser(record);

        }catch(Exception e){
            rn = -1;
        }

        return rn;
    }

    @Transactional(rollbackFor = {Exception.class})
    public int deleteUser(List<String> codeList){
        int rn = 0;
        try {
            rn = userMapper.delUserList(codeList);

        }catch(Exception e){
            rn = -1;
        }

        return rn;
    }

    @Transactional(rollbackFor = {Exception.class})
    public int updateUserStatus(String code, int status){
        int rn = 0;
        try {
            rn = userMapper.updateUserStatus(code, status);

        }catch(Exception e){
            rn = -1;
        }

        return rn;
    }
}
