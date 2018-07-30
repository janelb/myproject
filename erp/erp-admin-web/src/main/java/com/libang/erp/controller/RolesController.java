package com.libang.erp.controller;

import com.libang.erp.controller.controllerException.NotFoundException;
import com.libang.erp.dto.ResponseBean;
import com.libang.erp.entity.Permission;
import com.libang.erp.entity.Role;
import com.libang.erp.entity.RolePermission;
import com.libang.erp.exception.ServiceException;
import com.libang.erp.service.RolesPermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/7/27 12:47
 */
@Controller
@RequestMapping("/manage/roles")
public class RolesController {

    @Autowired

    private RolesPermissionService rolesPermissionService;

    //返回主页面

    @GetMapping

    public String home(Model model){
        /*TODO*/
        List<Role> roleList = rolesPermissionService.findRoleWithPermission();

            model.addAttribute("roleList",roleList);
        return "manage/roles/home";
    }



    //新增角色
    @GetMapping("/new")

    public String rolesNew(Model model){
        //获取权限列表
        List<Permission> permissionList = rolesPermissionService.findAll();
        model.addAttribute("permissionList",permissionList);
        return "manage/roles/new";
    }

    @PostMapping("/new")
    public String rolesNew(Role role,Integer[] permissionId){
            rolesPermissionService.saveRole(role,permissionId);
        return "redirect:/manage/roles";
    }

    //删除角色
    @GetMapping("/{id:\\d+}/del")
    @ResponseBody

    public ResponseBean roelsDel(@PathVariable Integer id){

        try {
            rolesPermissionService.delRoles(id);
          return ResponseBean.success();

        } catch (ServiceException e) {

          return ResponseBean.error(e.getMessage());

        }
    }



        /*当不修改是会出现buge*/
    //修改角色
    @GetMapping("/{id:\\d+}/edit")

    public String rolesUpdate(@PathVariable Integer id,Model model){

        Role role = rolesPermissionService.findByRoleId(id);
        if(role==null){
            throw new NotFoundException();
        }
        //获取所有被checked的权限列表
        Map<Permission , Boolean> permissionBooleanMap = rolesPermissionService.permissionBooleanMap(role.getPermissionList());
        model.addAttribute("permissionBooleanMap",permissionBooleanMap);
        model.addAttribute("role",role);



    /*
        List<Permission> permissionList = rolesPermissionService.findAll();
        List<RolePermission> rolePermissionList = rolesPermissionService.findListByPermissionId(id);
        permissionList.remove(role);
        model.addAttribute("role",role);
        model.addAttribute("permissionList",permissionList);
        model.addAttribute("rolePermissionList",rolePermissionList);
*/
        return "manage/roles/edit";
    }

    @PostMapping("/{id:\\d+}/edit")

    public String rolesUpdate( Integer[] permissionId ,Role role){
            rolesPermissionService.roleEdit(role,permissionId);
        return "redirect:/manage/roles";

    }




}
