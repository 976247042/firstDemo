package com.ljl.controller;

import com.ljl.dao.AutologinMapper;
import com.ljl.pojo.Autologin;
import com.ljl.pojo.AutologinExample;
import com.ljl.pojo.User;
import com.ljl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService user;

    @Autowired
    private HttpServletRequest request;


    @Autowired
    private AutologinMapper auto;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map login(@RequestBody Map resultMap,HttpServletResponse response)throws Exception{
        String username = (String)resultMap.get("username");
        String password = (String)resultMap.get("password");
        boolean checked = (boolean)resultMap.get("checked");
        //1.获取当前ip
        InetAddress addr = InetAddress.getLocalHost();
//        System.out.println(request.getRemoteAddr());
        Map map = new HashMap();
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length>0){
            for(Cookie c:cookies){
                if(c.getName().equals("token")){
                    String tk = c.getValue();
                    AutologinExample example = new AutologinExample();
                    example.createCriteria().andIpEqualTo(addr.getHostAddress()).andTokenEqualTo(tk);
                    List<Autologin> list = auto.selectByExample(example);
                    if(list!=null&&list.size()>0){
                        User u = user.findById(list.get(0).getUserid());
                        map.put("status",200);
                        map.put("token", tk);
                        request.getSession().setAttribute("user",u);
                        request.getSession().setAttribute("token",tk);
                    }else{
                        c.setMaxAge(-1);
                        map.put("status",500);
                        map.put("msg", "请重新输入用户名和密码登录");
                    }
                    return map;
                }
            }
        }
        User u = user.login(username, password);
        if(u == null){
            map.put("status",500);
            map.put("msg","用户名或者密码错误");
        }else{
            map.put("status",200);
            String time = System.currentTimeMillis()+"",
            token = DigestUtils.md5DigestAsHex(time.getBytes());
            map.put("token", token);
            request.getSession().setAttribute("user",u);
            request.getSession().setAttribute("token",token);
            if(checked){
                Autologin al = new Autologin();
                al.setCreatedate(new Date());
                al.setToken(token);
                al.setIp(addr.getHostAddress());
                al.setUserid(u.getId());
                //保存
                auto.insert(al);
                //保存到cookie中
                Cookie c = new Cookie("token",token);
                c.setMaxAge(60*60*24*7);
                response.addCookie(c);
            }
        }
        return map;

    }

}
