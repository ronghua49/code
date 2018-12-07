package com.haohua.controller;    /*
 * @author  Administrator
 * @date 2018/12/6
 */
import com.github.pagehelper.PageHelper;
import com.haohua.entity.User;
import com.haohua.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;
import java.text.ParseException;
import java.util.List;
@Controller
class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @PostMapping("/user/add")
    @ResponseBody
    public String addUser(@RequestBody User user) throws ParseException {
       System.out.println(user);
        userMapper.insert(user);
        System.out.println(user);
        return "success";
    }

    @PostMapping("/user/batch_add")
    @ResponseBody
    public String batchAddUser(@RequestBody List<User> users) throws ParseException {
        userMapper.insertList(users);
        return "success";
    }

    @GetMapping("/user/{id:\\d+}")
    @ResponseBody
    public User findById(@PathVariable Integer id){
       /* User user1 = new User();
        user1.setId(id);
        User user =userMapper.selectOne(user1);*/
        User user = userMapper.selectByPrimaryKey(id);
        System.out.println(user);
        return user;
    }
    /**
     * 根据姓名，年级，cardID 模糊查询Users
     * @param param
     * @return
     */
    @GetMapping("/users/{param:\\w+}")
    @ResponseBody
    public List<User> findByParam(@PathVariable Object param){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
       criteria.orLike("name","%"+(String) param+"%");
       criteria.orLike("age",(String) param);
       criteria.orLike("cardNo",(String) param);
        List<User> users = userMapper.selectByExample(example);
        System.out.println(users);
        return users;
    }
    /**
     * 按照年龄从大到小排序，分页
     * @return
     */
    @GetMapping("/users/all")
    @ResponseBody
    public List<User> findUserOrderByAge(){
        PageHelper.offsetPage(0, 2);
        Example example = new Example(User.class);
        example.setOrderByClause("age desc");
        return userMapper.selectByExample(example);
    }





}
