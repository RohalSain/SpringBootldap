package com.git.ldap.ldap.LdapSecurity;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchControls;

import com.git.ldap.ldap.Model.LdapUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

@Service
public class ldapCrudImple implements ldapCrudRepo {
    public static final String BASE_DN = "dc=springframework,dc=org";
    
    @Autowired
    LdapTemplate ldapTemplate;

    
    @Override
    public List<LdapUser> retrieve() {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        List<LdapUser> user = ldapTemplate.search(query().where("objectclass").is("person"),
                new PersonAttributeMapper());
        return user;
    }

    @Override
    public boolean create( LdapUser p) {
        final Name dn = buildDn(p.getUsername());
        try {
            ldapTemplate.bind(dn, null, buildAttributes(p));
            return true;
        } catch (Exception e) {
            System.out.println("Exception occured"+e);
            return false;
        }
    }

    @Override
    public boolean update(LdapUser p) {
        try {
            Name dn = buildDn(p.getUsername());
            ldapTemplate.rebind(dn, null, buildAttributes(p));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove( String email) {
        Name dn = buildDn(email);
        // ldapTemplate.unbind(dn, true); //Remove recursively all entries
        try {
            ldapTemplate.unbind(dn);
            return true;
        } catch(Exception e) {
            System.out.println("Error "+e);
            return false;
        }
    }

    public Name buildDn(final String email) {
		return LdapNameBuilder.newInstance(BASE_DN).add("ou", "people").add("uid", email).build();
    }
    
    private Attributes buildAttributes(LdapUser p) {

		BasicAttribute ocattr = new BasicAttribute("objectclass");
		ocattr.add("top");
        ocattr.add("person");
        ocattr.add("organizationalPerson");
        ocattr.add("inetOrgPerson");


		Attributes attrs = new BasicAttributes();
        attrs.put(ocattr);
        attrs.put("uid", p.getUsername());
        attrs.put("cn", p.getUsername());
        attrs.put("sn", p.getUsername());
        attrs.put("userPassword", p.getPassword());

        return attrs;
    }
    private class PersonAttributeMapper implements AttributesMapper<LdapUser> {
 
        @Override
        public LdapUser mapFromAttributes(Attributes attributes) throws NamingException {
            String email = attributes.get("uid") != null ? attributes.get("uid").get().toString(): "user email is not found";
            String username = attributes.get("cn") != null ? attributes.get("cn").get().toString(): "user name is not found";
            String userPassword = attributes.get("userPassword") != null ? attributes.get("userPassword").get().toString(): "user password is not found";
            LdapUser user = new LdapUser();
            user.setPassword(userPassword);
            user.setUsername(username);
        
            return user;
        }
    }
}