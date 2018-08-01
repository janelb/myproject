package com.libang.erp.shiro;

import com.libang.erp.entity.Permission;
import com.libang.erp.service.RolesPermissionService;
import org.apache.shiro.config.Ini;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/31 19:52
 */
public class CustomerFilterChainDefinition implements FactoryBean<Ini.Section> {

    @Autowired
    private RolesPermissionService rolesPermissionService;

    private String filterChainDefinitions;


    @Override
    public Ini.Section getObject() throws Exception {
        Ini ini = new Ini();
        ini.load(filterChainDefinitions);
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        //获取所有权限集合
        List<Permission> permissionList = rolesPermissionService.findAll();
        for(Permission permission : permissionList){

            //进行封装 /manage/employee/add = perms[employee:add]
            section.put(permission.getUrl(),"perms["+permission.getPermissionCode()+"]");
        }
            section.put("/**","user");

        return section;
    }

    @Override
    public Class<?> getObjectType() {
        return Ini.Section.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }


}
