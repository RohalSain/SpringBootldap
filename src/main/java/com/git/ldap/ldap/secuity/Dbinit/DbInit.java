package com.git.ldap.ldap.secuity.Dbinit;

import com.git.ldap.ldap.secuity.UserRepository;
import com.git.ldap.ldap.secuity.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
class DbInit implements CommandLineRunner {

@Autowired
private UserRepository userRepository;

@Autowired
private PasswordEncoder passwordEncoder;
    

@Override
    public void run(String... args) throws Exception {
         //userRepository.deleteAll();

         
         //User rohal = new User("id","ben", passwordEncoder.encode("benspassword"), "USER","");
         //User admin = new User("id","admin",passwordEncoder.encode("admin123"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
         //rohal.setId(rohal.UniqueIdGeneration());
         //admin.setId(admin.UniqueIdGeneration());
        //userRepository.save(rohal);
        //User user = userRepository.findByUsername("bens");
        //userRepository.save(admin);
        // //List<User> list = Arrays.asList(rohal,admin);
        // //userRepository.saveAll(list);
        // userRepository.save(rohal);
        // userRepository.save(admin);
        //User user=userRepository.findByUsername("rohal");
        //System.out.println(user);
    }
    @Bean
PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}

}