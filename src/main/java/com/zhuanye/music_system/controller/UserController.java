package com.zhuanye.music_system.controller;

import com.zhuanye.music_system.entity.User;
import com.zhuanye.music_system.entity.VerifyCode;
import com.zhuanye.music_system.service.UserService;
import com.zhuanye.music_system.support.ResultMessage;
import com.zhuanye.music_system.util.IVerifyCodeGen;
import com.zhuanye.music_system.util.SimpleCharVerifyCodeGenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * @auther:sabot
 * @date:2020/05/06
 * @description:User控制类
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Value("${picture_path}")
    private String picture_path;

    @Autowired
    private UserService userService;

    //获取登录用户信息
    @RequestMapping("/getLoginUser")
    @ResponseBody
    public User getLoginUser( HttpServletRequest request){
        User user = new User();
        if (request.getSession().getAttribute("user") != null){
            user = (User) request.getSession().getAttribute("user");
        }
        return user;
    }

    //根据用户id获取用户信息
    @RequestMapping(value = "/getUserByUserID/{id}",method = RequestMethod.GET)
    @ResponseBody
    public User getUserByUserID( @PathVariable Integer id){
        return userService.getUserByUserID(id);
    }

    //登录
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage login(String mailbox,String password,HttpServletRequest request){

        //调用service处理
        Map loginMap = userService.login(mailbox, password);
        if (loginMap.get("user") != null){
            request.getSession().setAttribute("user",(User)loginMap.get("user"));
        }

        return (ResultMessage) loginMap.get("resultMessage");
    }

    //验证码
    @RequestMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("VerifyCode");
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();

        } catch (IOException e) {}
    }

    //注册
    @RequestMapping("/registered")
    @ResponseBody
    public ResultMessage registered(String name, String mailbox, String password, String verifyCode,HttpServletRequest request){
        ResultMessage resultMessage = new ResultMessage();
        List<String> msg = new ArrayList<>();

        String code = (String) request.getSession().getAttribute("VerifyCode");
        request.getSession().removeAttribute("VerifyCode");

        if ( !verifyCode.equals(code)){
            msg.add("验证码错误");
            msg.add(code);
            resultMessage.setMsg(msg);
            return resultMessage;
        }
        if (userService.isExitmail(mailbox)){
            msg.add("该邮箱已注册");
            resultMessage.setMsg(msg);
            return resultMessage;
        }

        userService.registered(name,mailbox,password);
        msg.add("注册成功,激活邮件已发送至您的邮箱。");
        resultMessage.setMsg(msg);
        resultMessage.setFlag(true);
        return resultMessage;
    }

    //激活用户
    @RequestMapping(value = "/enabledUser/{id}",method = RequestMethod.GET)
    public String enabledUser(@PathVariable Integer id){
        userService.enabled(id);
        return "enabled";
    }

    //上传用户头像
    @RequestMapping("/uploadHead_picture")
    @ResponseBody
    public String uploadHeadPicture( MultipartFile file,HttpServletRequest request) throws IOException {

        String fileNewName =  UUID.randomUUID().toString()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = picture_path + fileNewName;
        File f = new File(url);
        if (!f.getParentFile().exists()){
            f.getParentFile().mkdirs();
        }
        f.createNewFile();
        file.transferTo(f.getAbsoluteFile());

        User user = (User) request.getSession().getAttribute("user");
        userService.uploadHead_picture(user.getId(),url);
        return url;


    }

    //根据url获取头像
    @RequestMapping("/getHeadPicture")
    public void getHeadPicture( String url , HttpServletResponse response) throws IOException {

        byte[] readAllBytes = Files.readAllBytes(new File(url).toPath());

        //设置响应头
        response.setHeader("Pragma", "no-cache");
        //设置响应头
        response.setHeader("Cache-Control", "no-cache");
        //在代理服务器端防止缓冲
        response.setDateHeader("Expires", 0);
        //设置响应内容类型
        response.setContentType("image/jpeg");
        response.getOutputStream().write(readAllBytes);
        response.getOutputStream().flush();

    }

    //修改密码
    @RequestMapping("/updatePassword")
    @ResponseBody
    public ResultMessage updatePassword(String password,String newPassword,HttpServletRequest request){

        ResultMessage resultMessage = new ResultMessage();
        List<String> msg = new ArrayList<>();
        User user = (User) request.getSession().getAttribute("user");

        if ( user.getPassword().equals(password)){
            user.setPassword(newPassword);
            userService.updatePassword(user);
            request.getSession().removeAttribute("user");
            request.getSession().setAttribute("user",user);
            resultMessage.setFlag(true);
        }else{
            msg.add("原密码不正确");
        }

        return resultMessage;
    }


    //修改资料
    @RequestMapping("/updateMessage")
    @ResponseBody
    public ResultMessage updateMessage(String name , Boolean gender , String phone , HttpServletRequest request){
        ResultMessage resultMessage = new ResultMessage();
        List<String> msg = new ArrayList<>();
        User user = (User) request.getSession().getAttribute("user");

        user.setName(name);
        user.setGender(gender);
        user.setPhone(phone);

        userService.updateMessage(user);

        request.getSession().removeAttribute("user");
        request.getSession().setAttribute("user",user);

        resultMessage.setFlag(true);

        return resultMessage;
    }

}
