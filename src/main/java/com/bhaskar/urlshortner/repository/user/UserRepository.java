package com.bhaskar.urlshortner.repository.user;

import com.bhaskar.urlshortner.model.user.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Profile, Long> {
}
