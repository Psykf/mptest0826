package com.mp.mptest0826;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mp.mptest0826.entity.User;
import com.mp.mptest0826.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest //自动生成的
public class Mptest0826ApplicationTests {

    //注入userMapper
    @Autowired
    private UserMapper userMapper;

    //1 查询user表所有记录
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    //2 添加操作
    @Test
    public void addUser(){
        User user = new User();
        user.setName("德莱联盟");
        user.setAge(18);
        user.setEmail("2321312@dsfs");

        int result =userMapper.insert(user);
        System.out.println(result);
    }

    //实现修改操作
    @Test
    public void updataUser(){
        User user = new User();
        user.setId(1207256273595179009L);
        user.setName("李磊发疯1111");

        int update = userMapper.updateById(user);
        System.out.println(update);
    }

    //测试乐观锁
    @Test
    public void testOptimisticLocker(){
        //1先查询
         User user = userMapper.selectById(1207262834375860225L);
        //2设置修改值
        user.setName("德莱联盟2");
        //3调用方法修改
        userMapper.updateById(user);
    }

    //测试多id查询
    @Test
    public void op(){
        List<User> user = userMapper.selectBatchIds(Arrays.asList(1,2,3));
        System.out.println(user);
        //user.forEach(System.out::println);
    }

    //条件查询
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //分页查询
    @Test
    public void tsetPage(){
        //1创建psge对象，传入当前页和每页记录数
        Page<User> page=new Page<>(1,3);
        //2调用mp中放方法实现分页查询
        //mp分页插件底层封装，把分页所有数据封装到page对象里面
        userMapper.selectPage(page,null);

        //第2中写法
        // IPage<User> user =userMapper.selectPage(page,null);
        //第3步，从分页对象中获得所有数据
        long current = page.getCurrent();//当前页
        List<User> records = page.getRecords(); //每页数据list的集合
        long size = page.getSize(); //每页记录数
        long total = page.getTotal(); //总记录数
        long pages = page.getPages(); //总页数

        boolean hasNext = page.hasNext(); //是否有下一页
        boolean hasPrevious = page.hasPrevious(); //是够有上一页

        System.out.println(current);
        System.out.println(records);
        System.out.println(size);
        System.out.println(total);
        System.out.println(pages);
        System.out.println(hasNext);
        System.out.println(hasPrevious);
    }

    //根据id删除（物理删除）
    @Test
    public void testUserId(){
       int result = userMapper.deleteById(3L);
        System.out.println(result);
    }

    //测试逻辑删除
    @Test
    public void testUserId2(){
      int i = userMapper.deleteById(1L);
        System.out.println(i);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    //测试条件查询
    @Test
    public void tsetSelectDemol(){
        //插件条件查询构造器
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        //wrapper.ge("age",28);
        //wrapper.eq("name", "Jone");
        wrapper.between("age",20,30);

        List<User> user = userMapper.selectList(wrapper);
        System.out.println(user);
    }


/**
 * 测试 性能分析插件
 */
    @Test
    public void testPerformance() {
        User user = new User();
        user.setName("我是Helen");
        user.setEmail("helen@sina.com");
        user.setAge(18);
        userMapper.insert(user);
    }


}
