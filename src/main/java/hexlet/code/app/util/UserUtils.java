package hexlet.code.app.util;

import hexlet.code.app.exception.ResourceNotFoundException;
import hexlet.code.app.model.User;
import hexlet.code.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {
    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        var email = authentication.getName();
        return userRepository.findByEmail(email).get();
    }

    public boolean isCreator(long id) {
        var creatorEmail = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This user id: " + id + "does not exist")).getEmail();
        var authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();
        return creatorEmail.equals(authenticationName);
    }

    public User getTestUser() {
        return userRepository.findByEmail("hexlet@example.com")
                .orElseThrow(() -> new RuntimeException("testUser does not exist"));
    }
}
