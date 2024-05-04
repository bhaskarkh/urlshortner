package com.bhaskar.urlshortner.services.user;

import com.bhaskar.urlshortner.model.user.Profile;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserServices {
    Optional<Profile> getUserByUserId(Long userId);
    BigDecimal getUserPoint(Long userId);
    BigDecimal increaseUserPoint(Long userId, BigDecimal productPrice);
    ResponseEntity<?> addUser(Profile profile);
    ResponseEntity<?> addUserList(List<Profile> profile);
    ResponseEntity<?> removeUser(Long userId);

}
