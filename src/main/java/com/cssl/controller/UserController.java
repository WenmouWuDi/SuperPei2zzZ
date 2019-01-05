package com.cssl.controller;

import com.cssl.service.ItemsService;
import com.cssl.service.OptionsService;
import com.cssl.service.SubjectService;
import com.cssl.service.UserService;
import com.cssl.vo.ItemsVo;
import com.cssl.vo.OptionsVo;
import com.cssl.vo.SubjectVo;
import com.cssl.vo.UserVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ItemsService itemsService;
    @Autowired
    private OptionsService optionsService;



    @RequestMapping("/User-login.action")
    public String userLogin(UserVo userVo,HttpSession session){
        UserVo user = userService.userLogin(userVo);
        ServletContext application = session.getServletContext();
        if(user != null){
            //登陆成功
            ArrayList<String> arrayList = (ArrayList<String>)application.getAttribute("users");
            if(arrayList.contains(user.getUsername())){
                //已登录
                return "login";
            }
            arrayList.add(user.getUsername());
            session.setAttribute("user",user);
            return "redirect:showAll";
        }
        //登录失败
            return "login";
    }
    @RequestMapping("/getUserByName")
    public void isExist(String username, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        int i = userService.isExist(username);
        out.write(i);
        out.flush();
        out.close();
    }
    @RequestMapping("/User-register.action")
    public String Register(UserVo userVo){
        int i = userService.userRegister(userVo);
        if(i > 0){
            return "login";
        }
        return "regist";
    }

    @RequestMapping("/showAll")
    public String showAll(HttpSession session,String title,Integer index,HttpServletRequest request){
        PageHelper.startPage(index==null?1:index,3);
        List<Map<String,Object>> all = subjectService.showAll(title == null ? "%" : title);
        session.setAttribute("LaiYuan","DengLu");
        session.setAttribute("title",title);
        session.setAttribute("all",all);
        return "votelist";
    }
    @RequestMapping("/showOne")
    public String showOne(HttpSession session,int id){
        List<Map<String,Object>> map = subjectService.showOne(id);
        Map<String,Object> map1 = subjectService.showTitle(id);
        session.setAttribute("map",map);
        session.setAttribute("map1",map1);
        return "voteview";
    }
    @RequestMapping("/ToCanYu")
    public String CanYu(HttpSession session,int id){
        Map<String,Object> map1 = subjectService.showTitle(id);
        List<Map<String,Object>> map = subjectService.showOne(id);
        session.setAttribute("map",map);
        session.setAttribute("map1",map1);
        return "vote";
    }

    @RequestMapping("/CanYu")
    public String CanYu(@RequestParam(value = "oid1") List<Integer> str, HttpSession session,int sid1){
        UserVo userVo = (UserVo) session.getAttribute("user");
        int num = userService.isTouPiao(userVo.getUid1(),sid1);
        if(num > 0){
            session.setAttribute("Wen","此投票您已经投过票了,请看看其他投票8");
            System.out.println(session.getAttribute("Wen"));
            return "result";
        }else{
            for(Integer str1 : str){
                ItemsVo itemsVo = new ItemsVo();
                itemsVo.setUid1(userVo.getUid1());
                itemsVo.setOid1(str1);
                itemsVo.setSid1(sid1);
                itemsService.Tou1(itemsVo);
            }
        }
        return "redirect:showOne?id="+sid1;
    }
    @RequestMapping("/LaiYuan")
    public String LiaYuan(HttpSession session,int index){
        UserVo userVo = (UserVo)session.getAttribute("user");
        int i = userService.isAdmin(userVo.getUid1());
        if(i > 0){
            //是管理员
            session.removeAttribute("LaiYuan");
            session.setAttribute("LaiYuan","WeiHu");
            return "redirect:Tiao?index="+index;
        }else{
            //不是管理员
            session.setAttribute("Wen","用户权限不足，无法进行维护");
            return "result";
        }

    }
    @RequestMapping("/Tiao")
    public String showAll1(HttpSession session,Integer index,HttpServletRequest request){
        String title = (String) session.getAttribute("title");
        PageHelper.startPage(index==null?1:index,3);
        List<Map<String,Object>> all = subjectService.showAll(title == null ? "%" : title);
        session.setAttribute("title",title);
        session.setAttribute("all",all);
        session.setAttribute("LaiYuan","WeiHu");
        return "votelist";
    }
    @RequestMapping("/WeiHui1")
    public String WeiHui1(HttpSession session,Integer id){
        Map<String,Object> map1 = subjectService.showTitle(id);
        List<Map<String,Object>> map = subjectService.showOne(id);
        session.setAttribute("map1",map1);
        session.setAttribute("map",map);
        return "add";
    }
    @RequestMapping("/xiugai")
    public String XiuGai(@RequestParam(value = "options") List<String> options,@RequestParam(value = "oid1")List<String> oid1 ,
                         @RequestParam(value="newoptions",required = false) List<String> list1,
                         Integer sid1,String subject,String title){
            List<Map<String,Object>> wen = subjectService.showOne(sid1);
        System.out.println("sid1:"+sid1);
        for(int i = 0; i< options.size();i++){
            System.out.println("upd"+oid1.get(i));
            OptionsVo optionsVo = new OptionsVo();
            optionsVo.setOsid(sid1);
            optionsVo.setOid1(Integer.valueOf(oid1.get(i)));
            optionsVo.setContent1(options.get(i));
            optionsService.XiuGaiOptions(optionsVo);
        }
        List<Map<String,Object>> wen1 = subjectService.showOne(sid1);
            for(Map<String,Object> str : wen1){
                System.out.println(oid1.contains(str.get("OID1").toString())+"...");
                    if(!(oid1.contains(str.get("OID1").toString()))){
                        System.out.println("del:"+options+"-"+str.get("CONTENT1")+"-"+str.get("OID1")+"-"+oid1);
                        itemsService.delPiao(Integer.valueOf(str.get("OID1").toString()));
                        optionsService.deleteOptions(Integer.valueOf(str.get("OID1").toString()));
                }
            }
        if(list1 != null && list1.size() > 0){
            for(String str : list1){
                System.out.println("add:"+str);
                OptionsVo optionsVo = new OptionsVo();
                optionsVo.setOsid(sid1);
                optionsVo.setContent1(str);
                optionsService.AddOptions(optionsVo);
            }
        }



        SubjectVo subjectVo = new SubjectVo();
        subjectVo.setSid1(sid1);
        subjectVo.setTitle(title);
        subjectVo.setType1(subject);
        subjectService.XiuGai(subjectVo);
        return "redirect:showAll";
    }

    @RequestMapping("/TianJia")
    public String TianJia(@RequestParam List<String> options,SubjectVo subjectVo){
        int i = subjectService.chaRu(subjectVo);
        if(i > 0){
            for(String str : options){
                OptionsVo optionsVo = new OptionsVo();
                optionsVo.setOsid(subjectVo.getSid1());
                optionsVo.setContent1(str);
                optionsService.AddOptions(optionsVo);
            }
        }
        return "redirect:showAll";
    }
    @RequestMapping("/Delete")
    public String Del(int id){
        itemsService.delPiaoBySid(id);
        optionsService.delOptionsBySid(id);
        subjectService.delSubjectBySid(id);
        return "redirect:showAll";
    }
    @RequestMapping("/XuanZe")
    public String XuanZe(HttpSession session){
        UserVo userVo = (UserVo)session.getAttribute("user");
        int i = userService.isAdmin(userVo.getUid1());
        System.out.println(userVo.getUid1()+"-"+i);
        if(i > 0){
            return "addTou";
        }else{
            session.setAttribute("Wen","用户权限不足，无法添加新投票");
            return "result";
        }
    }
    @RequestMapping("/ZhuXiao")
    public String ZhuXiao(HttpSession session){
        session.invalidate();
        return "login";
    }
}
