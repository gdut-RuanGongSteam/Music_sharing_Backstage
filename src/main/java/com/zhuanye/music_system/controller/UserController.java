package com.zhuanye.music_system.controller;

import com.zhuanye.music_system.entity.User;
import com.zhuanye.music_system.entity.VerifyCode;
import com.zhuanye.music_system.service.UserService;
import com.zhuanye.music_system.support.ResultMessage;
import com.zhuanye.music_system.util.IVerifyCodeGen;
import com.zhuanye.music_system.util.SimpleCharVerifyCodeGenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther:sabot
 * @date:2020/05/06
 * @description:User控制类
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserByUserID/{id}",method = RequestMethod.GET)
    public User getUserByUserID( @PathVariable Integer id){
        return userService.getUserByUserID(id);
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResultMessage login(String mailbox,String password){

        //调用service处理
        ResultMessage resultMessage = userService.login(mailbox, password);

        return resultMessage;
    }

    @RequestMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
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
}
