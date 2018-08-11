package com.libang.erp.shiro;

import com.libang.erp.entity.Permission;
import com.libang.erp.service.RolesPermissionService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/31 19:52
 */
public class CustomerFilterChainDefinition {

    //Ini.Section 是Ini的map 集合放入以值的形式放入section的map集合，
    // 通过Ini.load()方法来加载配置文件中的自定义的权限配置文件的规则

    @Autowired
    private RolesPermissionService rolesPermissionService;

    private String filterChainDefinitions;

    private AbstractShiroFilter shiroFilter;


    /**
     * 初始化权限
     */
    @PostConstruct
    public synchronized void init(){

        getFilterChainManager().getFilterChains().clear();
        load();
    }

    /**
     * 更新权限
     */
    public synchronized void updatePermission(){

        getFilterChainManager().getFilterChains().clear();
        load();
    }



    public synchronized void  load(){
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
        //获取defaultFilterChainManager，然后进行Ini.Section 的Map集合的迭代，设置到defaultFilterChainManager
        //底层源码与此相反
        DefaultFilterChainManager defaultFilterChainManager  = getFilterChainManager();
        for(Ini.Section.Entry<String ,String> entry : section.entrySet()){

            defaultFilterChainManager.createChain(entry.getKey(),entry.getValue());
        }
    }



    public DefaultFilterChainManager getFilterChainManager(){

        //从当前对象的shiroFilter通过getFilterChainResolver(),方法来获取pathMatchingFilterChainResolver，
        // 在通过getFilterChainManager()方法来获取defaultFIlterChainManager

        PathMatchingFilterChainResolver pathMatchingFilterChainResolver = (PathMatchingFilterChainResolver) this.shiroFilter.getFilterChainResolver();
        DefaultFilterChainManager defaultFilterChainManager  = (DefaultFilterChainManager) pathMatchingFilterChainResolver.getFilterChainManager();

        return defaultFilterChainManager;

    }





    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }
    public void setShiroFilter(AbstractShiroFilter shiroFilter) {
        this.shiroFilter = shiroFilter;
    }




}
