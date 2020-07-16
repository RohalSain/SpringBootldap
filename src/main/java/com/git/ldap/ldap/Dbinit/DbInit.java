package com.git.ldap.ldap.Dbinit;

import com.git.ldap.ldap.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Service
class DbInit implements CommandLineRunner {

@Autowired
private UserRepository userRepository;

    

@Override
    public void run(String... args) throws Exception {
          userRepository.deleteAll();

         
         //User rohal = new User("id","ben", passwordEncoder.encode("benspassword"), "USER","");
        // User admin = new User("id","ravi",passwordEncoder.encode("rohalravi"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
         //rohal.setId(rohal.UniqueIdGeneration());
         //admin.setId(admin.UniqueIdGeneration());
        //userRepository.save(rohal);
       // User user = userRepository.findByUsername("bens");
        //userRepository.save(admin);
        // //List<User> list = Arrays.asList(rohal,admin);
        // //userRepository.saveAll(list);
        // userRepository.save(rohal);
        // userRepository.save(admin);
        //User user=userRepository.findByUsername("rohal");
        //System.out.println(user);
    }
 

}