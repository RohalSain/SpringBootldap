package com.git.ldap.ldap.secuity;

import com.git.ldap.ldap.secuity.model.User;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
@N1qlPrimaryIndexed
@ViewIndexed(designDoc="user",viewName="all")
public interface UserRepository extends CouchbasePagingAndSortingRepository<User, String> {
    User findByUsername(String username);
}
