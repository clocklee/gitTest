package com.mis.infosys.persist.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mis.infosys.persist.entities.rbac.User;

@Service
public class UsrService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String usrName) throws UsernameNotFoundException {
		
		Usr usr = usrDao.findByUserName(usrName);
        if(usr == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        for(SysRole role:usr.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println(role.getName());
        }
       // UserDetails userDetails = new User(usrName, usrName, false, false, false, false, authorities);
        return new org.springframework.security.core.userdetails.User(usr.getName(),
                usr.getPwd(), authorities);
		
	}

}
