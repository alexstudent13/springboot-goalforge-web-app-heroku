package project.sbtest.controller;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.sbtest.Utility.RandomString;
import project.sbtest.Utility.UserNotFoundException;
import project.sbtest.Utility.Utility;
import project.sbtest.service.UserService;
import project.sbtest.models.User;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
public class ForgotPasswordController {
    private final UserService userService;
    private final JavaMailSender javaMailSender;
    @Autowired
    public ForgotPasswordController(UserService userService, JavaMailSender javaMailSender){
        this.userService = userService;
        this.javaMailSender = javaMailSender;
    }
    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(){
        return "password_recovery";
    }
    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);
        System.out.println("Generated token: " + token);

        try {
            userService.updateResetPasswordToken(token, email);
            User user = userService.getUserByEmail(email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            System.out.println("Reset password link: " + resetPasswordLink);

            sendMail(email, resetPasswordLink, user.getFirstName(), token);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check inbox your inbox or spam.");
        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "password_recovery";
    }
    @GetMapping("/reset_password")
    public String showResetPasswordForm(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        User user = userService.getByResetPasswordToken(token);
        if (user == null) {
            model.addAttribute("error", "Invalid token. Please try again or request a new password reset.");
            return "password_recovery";
        }
        model.addAttribute("token", token);
        return "password_change";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        User user = userService.getByResetPasswordToken(token);
        if (user == null) {
            model.addAttribute("error", "Invalid token. Please try again or request a new password reset.");
            return "password_recovery";
        }
        userService.updatePassword(user, password);
        model.addAttribute("message", "Your password has been successfully updated.");
        return "redirect:/login"; // Redirect to login page
    }
    private void sendMail(String recipientEmail, String resetPasswordLink, String firstName, String token) {
        final String emailToRecipient = recipientEmail;
        // Generate a unique random string as the identifier
        String uniqueIdentifier = generateRandomString(12); // You can adjust the length of the random string as needed
        final String emailSubject = "Password Link Recovery - " + uniqueIdentifier;

        // Append the unique identifier to the reset password link
        String resetPasswordLinkWithIdentifier = resetPasswordLink + "&id=" + uniqueIdentifier;

        final String emailMessage = "<p>Hello " + firstName + ",</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + resetPasswordLinkWithIdentifier + "\">Reset Password Link</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request for password change.</p>";
        javaMailSender.send(new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMsgHelperObj.setTo(emailToRecipient);
                mimeMsgHelperObj.setText(emailMessage, true);
                mimeMsgHelperObj.setSubject(emailSubject);
            }
        });
    }
    private String generateRandomString(int length) {
        // Generate a random string of specified length using SecureRandom and Base64 encoding
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}

