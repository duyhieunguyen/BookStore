package pq.jdev.b001.bookstore.users.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pq.jdev.b001.bookstore.users.model.Mail;
import pq.jdev.b001.bookstore.users.model.PasswordResetToken;
import pq.jdev.b001.bookstore.users.model.Person;
import pq.jdev.b001.bookstore.users.repository.PasswordResetTokenRepository;
import pq.jdev.b001.bookstore.users.service.EmailService;
import pq.jdev.b001.bookstore.users.service.UserService;
import pq.jdev.b001.bookstore.users.web.dto.PasswordForgotDto;

@Controller
@RequestMapping("/forgot-password")
public class PasswordFogotController {
	 @Autowired private UserService userService;
	    @Autowired private PasswordResetTokenRepository tokenRepository;
	    @Autowired private EmailService emailService;

	    @ModelAttribute("forgotPasswordForm")
	    public PasswordForgotDto forgotPasswordDto() {
	        return new PasswordForgotDto();
	    }

	    @GetMapping
	    public String displayForgotPasswordPage() {
	        return "forgot-password";
	    }

	    @PostMapping
	    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
	                                            BindingResult result,
	                                            HttpServletRequest request) {

	        if (result.hasErrors()){
	            return "forgot-password";
	        }

	        Person person = userService.findByEmail(form.getEmail());
	        if (person == null){
	            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
	            return "forgot-password";
	        }

	        PasswordResetToken token = new PasswordResetToken();
	        token.setToken(UUID.randomUUID().toString());
	        token.setPerson(person);
	       
	        token.setExpiryDate();
	        tokenRepository.save(token);

	        Mail mail = new Mail();
	        mail.setFrom("abc@gmail.com");
	        mail.setTo(person.getEmail());
	        mail.setSubject("Password reset request");

	        Map<String, Object> model = new HashMap<>();
	        model.put("token", token);
	        model.put("person", person);
	        model.put("signature", "http://localhost:8090");
	        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
	        mail.setModel(model);
	        emailService.sendEmail(mail);

	        return "redirect:/forgot-password?success";

	    }
}