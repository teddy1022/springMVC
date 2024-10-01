package com.systex.playground.repository;

import com.systex.playground.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Member, Integer> {
	 Member findByUsername(String username);
}
