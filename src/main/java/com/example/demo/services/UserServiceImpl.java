package com.example.demo.services;

import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author Nicole
 */
@Service
@Repository("userService")
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Override
    public List<User> findByName(String userName) {
        Example example = new Example(User.class);
        //可使用criteria完成动态sql拼接,example用于添加条件，相当where后面的部分
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("username=", userName);

        //相当于select * from user where username = 'userName'
        List<User> userList = this.selectByExample(example);
        if (userList.size() != 0) {
            return userList;
        } else {
            return null;
        }
    }


    @Override
    public void saveUser(User user) {
        user.setCreate_time(new Date());
        this.save(user);
    }
}
