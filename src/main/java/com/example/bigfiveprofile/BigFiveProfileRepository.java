package com.example.bigfiveprofile;


import org.springframework.data.repository.CrudRepository;

import com.example.bigfiveprofile.BigFiveProfile;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface BigFiveProfileRepository extends CrudRepository<BigFiveProfile, Integer> {

}

