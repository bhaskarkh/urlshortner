package com.bhaskar.urlshortner.services.user;

import com.bhaskar.urlshortner.exception.user.UserNotFoundException;
import com.bhaskar.urlshortner.exception.user.UserSaveFailedException;
import com.bhaskar.urlshortner.model.common.GlobalConstant;
import com.bhaskar.urlshortner.model.user.Profile;
import com.bhaskar.urlshortner.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<Profile> getUserByUserId(Long userId) {
        Optional<Profile> user = userRepository.findById(userId);
        if (user.isPresent()) return user;
        throw new UserNotFoundException();
    }

    @Override
    public BigDecimal getUserPoint(Long userId) {
        return getUserByUserId(userId).map(profile -> profile.getPoint()).orElse(null);
    }

    @Override
    public BigDecimal increaseUserPoint(Long userId, BigDecimal productPrice) {
        BigDecimal newPoint = getNewPointAfterAddition(getUserPoint(userId), productPrice);
        Profile profile = userRepository.findById(userId).orElse(null);
        profile.setPoint(newPoint);
        if (profile != null) {
            Profile savedProfile = userRepository.save(profile);
            if (savedProfile != null && savedProfile.getPoint() != null) return savedProfile.getPoint();
        }
        throw new UserNotFoundException();
    }

    @Override
    public ResponseEntity<?> addUser(Profile profile) {
        Profile user = userRepository.save(profile);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        throw new UserSaveFailedException();
    }

    @Override
    public ResponseEntity<?> addUserList(List<Profile> profileList) {
        List<Profile> userList = userRepository.saveAll(profileList);
        if (userList != null) {
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
        throw new UserSaveFailedException();
    }

    @Override
    public ResponseEntity<?> removeUser(Long userId) {
        return null;
    }

    private BigDecimal getNewPointAfterAddition(BigDecimal oldPoint, BigDecimal productPrice) {
        return oldPoint.add(productPrice.multiply(GlobalConstant.BONUS_PERCENT));
    }
}
