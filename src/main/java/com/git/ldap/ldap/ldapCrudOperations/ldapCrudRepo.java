package com.git.ldap.ldap.ldapCrudOperations;

import java.util.List;

import com.git.ldap.ldap.secuity.model.LdapUser;


public interface ldapCrudRepo {
 
    public List<LdapUser> retrieve();
    public boolean create(LdapUser p);
    public boolean update(LdapUser p);
    public boolean remove(String uid);
}