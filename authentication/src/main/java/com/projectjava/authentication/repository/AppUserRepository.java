package com.projectjava.authentication.repository;

import com.projectjava.authentication.Model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppUserRepository extends MongoRepository<AppUser, String> {
    Boolean existsAppUserByUsernameEquals(String userName);
    AppUser findByUsernameEquals(String userName);
}
