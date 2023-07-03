package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


import com.cg.entity.User;

@EnableJpaRepositories  //not compulsory
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

//    public RegistrationDto findByEmailId(String emailId);
//	
//	Optional<User> findByEmailIdAndPassword(String emailId, String password);
//
//	public RegistrationDto save(@Valid RegistrationDto registrationDTO);
//	
//	Optional<User> findByPassword(String password);
	User findByEmailId(String emailId);
}
