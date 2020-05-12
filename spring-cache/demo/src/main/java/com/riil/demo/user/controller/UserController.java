package com.riil.demo.user.controller;

import com.riil.demo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.riil.demo.user.pojo.User;

import java.util.List;
/**
 * {class description}
 * <br>
 * <p>
 * Create on : 2020/4/21 14:22 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 * <br>
 */
@Controller
public class UserController {

    @Autowired
    IUserService iUserService;

    @RequestMapping("/cache")
    @ResponseBody
    public Object cached() {
        ModelAndView mv = new ModelAndView();
        List<User> users = iUserService.getUser();
        return users;
    }
    @RequestMapping("/cacheRe")
    @ResponseBody
    public Object cachedRe() {
        ModelAndView mv = new ModelAndView();
        List<User> users = iUserService.getUserRe();
        return users;
    }


    @RequestMapping("/clear")
    @ResponseBody
    public String clear() {
        iUserService.clearCache();
        return "cache clear success";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insert(String name,String addrs) {
        try {
            iUserService.insert(name,addrs);
            return "insert success";
        } catch (Exception e) {
            e.printStackTrace();
            return "insert loser";
        }

    }
    @RequestMapping("/insertRe")
    @ResponseBody
    public String insertRe(String name,String addrs) {
        try {
            iUserService.insertRe(name,addrs);
            return "insertRe success";
        } catch (Exception e) {
            e.printStackTrace();
            return "insertRe loser";
        }

    }

}
