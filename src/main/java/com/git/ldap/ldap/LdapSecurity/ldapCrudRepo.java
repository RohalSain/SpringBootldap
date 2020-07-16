package com.git.ldap.ldap.LdapSecurity;

import java.util.List;

import com.git.ldap.ldap.Model.LdapUser;


public interface ldapCrudRepo {
 
    public List<LdapUser> retrieve();
    public boolean create(LdapUser p);
    public boolean update(LdapUser p);
    public boolean remove(String uid);
}