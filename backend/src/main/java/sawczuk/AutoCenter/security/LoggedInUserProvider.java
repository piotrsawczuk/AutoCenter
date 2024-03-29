package sawczuk.AutoCenter.security;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
public class LoggedInUserProvider {

    public static String findLoggedInUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public static boolean hasLoggedInRoleAdmin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            for (GrantedAuthority role : ((UserDetails) principal).getAuthorities()) {
                if (role.getAuthority().equals("admin")) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
}
