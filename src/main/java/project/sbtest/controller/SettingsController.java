package project.sbtest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.sbtest.models.User;
import project.sbtest.service.UserService;

@RestController
@RequestMapping("/user")
public class SettingsController {

    private final UserService userService;

    public SettingsController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/updateImage")
//    public ResponseEntity<?> updateImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
//        try {
//            userService.updateUserPhoto(id, file);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user photo: " + e.getMessage());
//        }
//    }
}

