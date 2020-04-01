package com.yinuo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inuol.user.Roles;
import com.yinuo.user.mapper.RolesMapper;


@Service
@Transactional
public class RolesService {

    @Autowired
    private RolesMapper rolesMapper;

    public void addAuthUrl(Roles roles){
       this.rolesMapper.insert(roles);
    }

    public void updateRoles(Roles roles){
        this.rolesMapper.updateByPrimaryKey(roles);
   }

    public Page<Roles> findAllRoles(int page, int size){
        PageHelper.startPage(page, size);
        List<Roles> list = rolesMapper.selectAll();
        if (list.size()>0) {
            return (Page<Roles>) list;
        }
        return null;
    }


    public List<Roles> findAllRoles(){
       return rolesMapper.selectAll();
    }


    public Roles getRolesByName(String name){
        Roles record = new Roles();
        record.setName(name);
        return rolesMapper.selectOne(record);
    }

    public Roles getRolesById(Long id){
        return rolesMapper.selectByPrimaryKey(id);
    }

}
