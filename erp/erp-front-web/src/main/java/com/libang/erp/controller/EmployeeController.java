package com.libang.erp.controller;


import com.google.common.collect.Maps;
import com.libang.erp.entity.Employee;
import com.libang.erp.entity.Role;
import com.libang.erp.service.EmployeeService;
import com.libang.erp.service.RoleEmployeeService;
import com.libang.erp.service.RolesPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * @author libang
 * @date 2018/7/25 9:24
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping("/manage/employ")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RolesPermissionService rolesPermissionService;

    @Autowired
    private RoleEmployeeService roleEmployeeService;

    //查询员工列表
    @GetMapping
    public String EmployList(Model model,
                             @RequestParam(required = false) Integer roleId,
                             @RequestParam(required = false) String nameMobile,
                             /*@RequestParam(required = false) Integer state*/
                             @RequestParam(required = false) String state
                             ){
        Map<String, Object> requestMap  = Maps.newHashMap();

            if(Employee.EMPLOYEE_FROZEN.equals(state)){
                requestMap.put("state",Employee.EMPLOYEE_STATE_FROZEN);
            }
            if(Employee.EMPLOYEE_NORMAL.equals(state)){
                requestMap.put("state",Employee.EMPLOYEE_STATE_NORMAL);
            }

        requestMap.put("nameMobile",nameMobile);
        requestMap.put("roleId",roleId);

        List<Employee> employeeList = employeeService.findAllAccountWithRolesByQueryParam(requestMap);
        List<Role> roleList = rolesPermissionService.findAllRoles();

            model.addAttribute("employeeList",employeeList);
            model.addAttribute("roleList",roleList);
              return "manage/employee/list";
    }





     /*===================新增员工==========================*/

    @GetMapping("/add")
   /* @RequiresPermissions("employee:add")*/
    public String addEmploy(Model model){
            List<Role> roleList = rolesPermissionService.findAllRoles();
            model.addAttribute("roleList",roleList);
             return "manage/employee/new";
    }


    @PostMapping("/add")
  /*  @RequiresPermissions("employee:add")*/
    public String addEmploy(Employee employee,Integer[] roleIds){
        employeeService.save(employee,roleIds);
        return"redirect:/manage/employ";
    }





    /*========================修改员工信息===============================*/




    @GetMapping("/{id:\\d+}/edit")
    public String edit(@PathVariable Integer id,Model model){

        Employee employee = employeeService.findById(id);
        //查询所有的角色信息
        List<Role> roleList = rolesPermissionService.findAllRoles();
        //查询当前站好拥有的角色
        List<Role> employeeRoleList = roleEmployeeService.findRoleWithEmployeeById(id);
        model.addAttribute("roleList",roleList);
        model.addAttribute("employeeRoleList",employeeRoleList);
        model.addAttribute("employee",employee);
        return "/manage/employee/edit";
    }





    @PostMapping("/{id:\\d+}/edit")
    public String edit(Employee employee,Integer[]  roleIds,RedirectAttributes redirectAttributes){

        employeeService.update(employee,roleIds);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/manage/employ";
    }





    /*============================删除员工=============================*/

    /*删除*/

    @GetMapping("/{id:\\d+}/del")

    public String del(@PathVariable Integer id,RedirectAttributes redirectAttributes){

        employeeService.delById(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/manage/employ";
    }


    /*=======================员工登录======================*/
    //员工登录

    @GetMapping("/home")
    public String home(){
        return "home";
    }


    @GetMapping("/login")
    public String login(){

        Subject subject = SecurityUtils.getSubject();
        //判断是否被认证
        if(subject.isAuthenticated()){
            subject.logout();
        }
        //是否被记住
        if(subject.isRemembered()){
            return "home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(String tell,
                        String password,
                        String remeber,
                        String remeberMe,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        HttpSession session,
                        RedirectAttributes redirectAttributes){
        /*使用shiro*/
        //创建suject主体对象
        Subject subject =SecurityUtils.getSubject();
        //获取登录的IP
        String loginIp = request.getRemoteAddr();

        //通过tell、password封装UsernamePasswordToken对象进行登录
        UsernamePasswordToken usernamePasswordToken  = new UsernamePasswordToken(tell,password,remeberMe!=null,loginIp);

        try{
                //进行登录
            subject.login(usernamePasswordToken);

            if(StringUtils.isNotEmpty(remeber)){

                Cookie cookie = new Cookie("tell",tell);
                cookie.setDomain("localhost");
                cookie.setPath("/");
                cookie.setMaxAge(60*60*24*7);
                cookie.setHttpOnly(true);
                response.addCookie(cookie);

            }
            //跳转到登录界面请求

            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            String url = "/manage/employ/home";
            if(savedRequest !=null ){
                // 获得callback的url
               url = savedRequest.getRequestUrl();
            }

            return "redirect:"+url;

            }catch(UnknownAccountException | IncorrectCredentialsException e){
                    redirectAttributes.addFlashAttribute("message","用户名或密码错误");
            }catch (LockedAccountException e){
                redirectAttributes.addFlashAttribute("message",e.getMessage());
            }catch (AuthenticationException e) {
             redirectAttributes.addFlashAttribute("message", "登录失败");
            }

        return "redirect:/manage/employ/login";
        /*以前做法*/
       /* String loginIp = request.getRemoteAddr();

        try{

        Employee employee = employeeService.login(tell,password,loginIp);

        session.setAttribute("employee",employee);
        if(StringUtils.isNotEmpty(remeber)){

            Cookie cookie = new Cookie("tell",tell);
            cookie.setDomain("localhost");
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24*7);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            }else{
            Cookie[] cookies = request.getCookies();
            if(cookies!=null){
                for(Cookie cookie : cookies){
                    cookie.setDomain("localhost");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return "redirect:/manage/employ/home";
        }catch(ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:/manage/employ/login";
        }*/
    }


    //退出登录
    @GetMapping("/loginout")

    public String loginOut(RedirectAttributes redirectAttributes,
                           HttpServletRequest request,
                           Model model){
        Subject subject =SecurityUtils.getSubject();

        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie : cookies){
                if("tell".equals(cookie.getName())){
                    model.addAttribute("tell",cookie.getValue());
                    break;
                }
            }
        }
        subject.logout();
        redirectAttributes.addFlashAttribute("message","你已退出请重新登录");
        return "login";
    }




 /*   @GetMapping("/loginout")

    public String loginOut(HttpSession session,
                           HttpServletRequest request,
                           Model model){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie : cookies){
                if("tell".equals(cookie.getName())){
                     model.addAttribute("tell",cookie.getValue());
                     break;
                }
            }
        }
        session.invalidate();
        return "login";
    }*/





    //冻结
    @GetMapping("/{id:\\d+}/frozen/{state:\\d+}")

    public String  frozen(
            @PathVariable Integer id,
            @PathVariable Integer state,
            Model model
            ){

         Employee employee = employeeService.frozenById(id,state);
         model.addAttribute("employee",employee);

        return "redirect:/manage/employ";
    }

    //没有权限时
    @GetMapping("/401")

    public String unauthorizedUrl(){

        return "/error/401";
    }

    /*==========================修改个人信息=======================================*/

        @GetMapping("/{id:\\d++}/set")
        public  String update(@PathVariable Integer id ,Model model){

            Employee employee = employeeService.findById(id);
            model.addAttribute("employee",employee);
            return "set";
        }
        @PostMapping("/{id:\\d++}/set")
        public String update(Employee employee){
            employeeService.set(employee);

            return "login";
        }





}
