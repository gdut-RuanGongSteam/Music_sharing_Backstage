package com.zhuanye.music_system.service;

import com.zhuanye.music_system.dao.UserDao;
import com.zhuanye.music_system.entity.User;
import com.zhuanye.music_system.support.ResultMessage;
import com.zhuanye.music_system.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
public class UserService{

    @Autowired
    private UserDao userDao;

    //根据id获取用户信息
    public User getUserByUserID(Integer id){
        return userDao.getUserByUserID(id);
    }

    //登录
    public Map login(String mailbox, String password){

        Map<String,Object> resultMap = new HashMap();
        ResultMessage resultMessage = new ResultMessage();
        List<String> msg = new ArrayList<>();

        //数据库查询用户
        User userByEmail = userDao.getUserByEmail(mailbox);

        if (userByEmail == null){
            msg.add("用户不存在");
        }else{
            if (userByEmail.getPassword().equals(password)){

                if (userByEmail.isEnabled() == true){
                    resultMessage.setFlag(true);
                    resultMap.put("user",userByEmail);
                }else{
                    msg.add("账号未激活");
                }

            }else{
                msg.add("密码错误");
            }
        }
        resultMessage.setMsg(msg);
        resultMap.put("resultMessage",resultMessage);

        return resultMap;
    }

    //查询邮箱是否存在
    public boolean isExitmail(String mailbox){

        if (userDao.getUserByEmail(mailbox) == null){
            return false;
        }else {
            return true;
        }
    }

    //注册
    public void registered(String name, String mailbox , String password){

        User user = new User();
        user.setName(name);
        user.setMailbox(mailbox);
        user.setPassword(password);
        user.setEnabled(false);
        user.setCreate_data(new Date());
        userDao.addUser(user);

        MailUtil.send(mailbox,
                "欢迎加入共享音乐！请激活你的账号",
                "欢迎你加入我们这个大家庭，<a href='http://120.24.35.66:8080/music_system/user/enabledUser/"+user.getId()+"'>点我</a>激活账号。",
                "smtp", "smtp.163.com", "sabot_v@163.com", "465", "sabot_v@163.com", "meiyoumima1203");


    }

    //激活账号
    public void enabled(Integer id){
        userDao.enabled(id);
    }

    //更新头像
    public void uploadHead_picture(Integer id, String head_picture){
        User user  = new User();
        user.setId(id);
        user.setHead_picture(head_picture);
        userDao.uploadHeadPicture(user);
    }

    //修改密码
    public void updatePassword(User user){
        userDao.updatePassword(user);
    }

    //修改资料
    public void updateMessage(User user){

        userDao.updateMessage(user);
    }

    public void increaseShareNum(Integer id) {
        userDao.increaseShareNum(id);
    }
}
