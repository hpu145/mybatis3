package com.kaishengit.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.User;
import com.kaishengit.entity.UserExample;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangyu on 2017/10/27.
 */
public class UserMapperTest {
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void init() {
        sqlSession = MyBatisUtil.getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }
    @After
    public void close() {
        sqlSession.close();
    }
    @Test
    public void countByExample() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andCountryIdEqualTo(1);
        Long num = userMapper.countByExample(userExample);
        System.out.println("查询countryId为1的总行数：" + num);
    }
    @Test
    public void deleteByExample() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(Arrays.asList(1,9,22));
        userMapper.deleteByExample(userExample);
        sqlSession.commit();
    }
    @Test
    public void insert() {
        User user = new User();
        user.setUserName("张华");
        user.setAddress("周口");
        user.setPassword("1001");
        user.setCountryId(1);
        //userMapper.insert(user);
        userMapper.insertSelective(user);
        sqlSession.commit();
    }
    @Test
    public void selectByExample() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdLessThan(10).andAddressEqualTo("河南");
        List<User> userList = userMapper.selectByExample(userExample);
        for (User user : userList) {
            System.out.println("id小于10的用户：" + user);
        }

    }
    @Test
    public void selectByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println("根据主键查找用户：" + user);
    }
    @Test
    public void updateByExample() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(3);
        User user = new User();
        user.setPassword("333");
        userMapper.updateByExampleSelective(user,userExample);
        sqlSession.commit();
    }
    @Test
    public void updateByPrimaryKey() {
        User user = new User();
        user.setId(3);
        user.setUserName("杰克");
        userMapper.updateByPrimaryKeySelective(user);
        sqlSession.commit();
    }
    //查询全部
    @Test
    public void selectAll() {
        UserExample userExample = new UserExample();
        List<User> userList = userMapper.selectByExample(userExample);
        for (User user : userList) {
            System.out.println("查询全部：" + user);
        }
        System.out.println("总共：" + userList.size());
    }
    //带有or的查询
    @Test
    public void selectByOR() {
        UserExample userExample = new UserExample();
        userExample.or().andCountryIdEqualTo(3);
        userExample.or().andAddressEqualTo("河南");
        List<User> userList = userMapper.selectByExample(userExample);
        for (User user : userList) {
            System.out.println("or查询：" + user);
        }
        System.out.println("总共：" + userList.size());

    }
    //利用pagehelper插件进行分页
    @Test
    public void page() {
        //第1页，取5条数据
        //PageHelper.startPage(1,5);
        PageHelper.offsetPage(0,5);
        UserExample userExample = new UserExample();
        List<User> userList = userMapper.selectByExample(userExample);
        for (User user : userList) {
            System.out.println("分页：" + user);
        }
        //转化为PageInfo对象
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        System.out.println("页数：" + userPageInfo.getPages());
        for(User user : userPageInfo.getList()) {
            System.out.println(user.getId() + " -> " + user.getUserName());
        }
    }
    @Test
    public void pageTwo() {
        //pageSize指每页几条，pageNum指分页的第几页
        List<User> userList = userMapper.page(5,3);
        for (User user : userList) {
            System.out.println("指定分页：" + user);
        }
    }

}
