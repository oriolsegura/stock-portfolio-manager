package com.oriolsegura.opulentia.repository;

import com.oriolsegura.opulentia.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Account> findAllByUserId(Long userId);

}
