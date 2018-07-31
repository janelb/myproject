package com.libang.erp.controller;

import com.google.common.collect.Lists;
import com.libang.erp.controller.controllerException.NotFoundException;
import com.libang.erp.dto.ResponseBean;
import com.libang.erp.entity.Permission;
import com.libang.erp.exception.ServiceException;
import com.libang.erp.service.RolesPermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/27 12:48
 */
@RequestMapping("/manage/permission")
@Controller
public class PermissionController {

    @Autowired
    private RolesPermissionService rolesPermissionService;

    //获取所有权限列表

    @GetMapping
    public String home(Model model) {
        List<Permission> permissionList = rolesPermissionService.findAll();

        System.out.println("111111" + permissionList);

        model.addAttribute("permissionList", permissionList);
        return "manage/permission/home";
    }

    //新增权限
    @GetMapping("/new")

    public String permisssionNew(Model model) {
        //封装所有菜单权限列表
        List<Permission> menuPermissionList = rolesPermissionService.findPermissionListByType(Permission.PERMISSION_TYPE_MENU);
        model.addAttribute("menuPermissionList", menuPermissionList);
        return "manage/permission/new";
    }

    @PostMapping("/new")
    public String permisssionNew(Permission permission) {

        rolesPermissionService.savePermission(permission);
        return "redirect:/manage/permission";
    }

    /*删除权限*/

    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public ResponseBean del(@PathVariable Integer id) {
        try {
            rolesPermissionService.delPermission(id);

        } catch (ServiceException e) {
            return ResponseBean.error(e.getMessage());
        }
        return ResponseBean.success();
    }

    /*修改权限*/
    @GetMapping("/{id:\\d+}/edit")
    /*@ResponseBody*/

    public String permissionUpdate(@PathVariable Integer id, Model model) {

        Permission permission = rolesPermissionService.findByPermissionId(id);
        if (permission == null) {
            /*throw new NullPointerException();*/
            throw new NotFoundException();
        }

        //封装菜单权限列表
        List<Permission> menuPermissionList = rolesPermissionService.findPermissionListByType(Permission.PERMISSION_TYPE_MENU);

        //排除当前的permission对象
        menuPermissionList.remove(permission);
        //删除当前对象的子对象
        remove(menuPermissionList, permission);

        model.addAttribute("permission", permission);
        model.addAttribute("menuPermissionList", menuPermissionList);
        return "manage/permission/edit";
    }

    /*使用递归方式进行循环迭代删除*/

    private void remove(List<Permission> menuPermissionList, Permission permission) {
        List<Permission> temp = Lists.newArrayList(menuPermissionList);

        for (int i = 0; i < temp.size(); i++) {

            if (temp.get(i).getPid().equals(permission.getId())) {
                remove(menuPermissionList, temp.get(i));
            }
            menuPermissionList.remove(permission);
        }
    }


    @PostMapping("/{id:\\d+}/edit")

    public String permissionUpdate(Permission permission) {
        rolesPermissionService.permissionEdit(permission);
        return "redirect:/manage/permission";
    }


}
