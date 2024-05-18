package com.bhaskar.urlshortner.controller.user;

import com.bhaskar.urlshortner.model.product.Product;
import com.bhaskar.urlshortner.model.user.Profile;
import com.bhaskar.urlshortner.services.product.ProductServicesImpl;
import com.bhaskar.urlshortner.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class ProfileController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("addUserList")
    public ResponseEntity<?> addUserList(@RequestBody List<Profile> profileList) {
        return userService.addUserList(profileList);
    }

    @PostMapping("addUser")
    public ResponseEntity<?> addUser(@RequestBody Profile profile) {
        return userService.addUser(profile);
    }
}
