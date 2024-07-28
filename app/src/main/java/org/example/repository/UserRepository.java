package org.example.repository;

import org.example.entites.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInfo, Long> {

    public UserInfo findByUsername(String userName);

}
