package com.oriolsegura.opulentia.repository;

import com.oriolsegura.opulentia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
