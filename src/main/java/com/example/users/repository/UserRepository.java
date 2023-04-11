package com.example.users.repository;



import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.users.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findByOrderByUserName(Pageable list);

}
