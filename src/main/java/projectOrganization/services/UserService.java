package projectOrganization.services;

import projectOrganization.entity.PasswordResetToken;
import projectOrganization.entity.RegisteredUser;
import projectOrganization.entity.Role;
import projectOrganization.repository.PasswordTokenRepository;
import projectOrganization.repository.RegisteredUserRepository;
import projectOrganization.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    RegisteredUserRepository registeredUserRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    PasswordTokenRepository passwordTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        RegisteredUser registeredUser = registeredUserRepository.findByMail(mail);

        if (registeredUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return registeredUser;
    }

    public RegisteredUser findUserByMail(String mail) {
        RegisteredUser user = registeredUserRepository.findByMail(mail);
        if(user == null ) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return user;
    }

    public RegisteredUser findUserById(Long userId) {
        Optional<RegisteredUser> userFromDb = registeredUserRepository.findById(userId);
        return userFromDb.orElse(new RegisteredUser());
    }

    public List<RegisteredUser> allUsers() {
        return registeredUserRepository.findAll();
    }

    public boolean saveUser(RegisteredUser user) {
        RegisteredUser userFromDB = registeredUserRepository.findByMail(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        registeredUserRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (registeredUserRepository.findById(userId).isPresent()) {
            registeredUserRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<RegisteredUser> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM RegisteredUser u WHERE u.registered_user_id > :paramId", RegisteredUser.class)
                .setParameter("paramId", idMin).getResultList();
    }

    public void createPasswordResetTokenForUser(RegisteredUser user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(user, token);
        passwordTokenRepository.save(myToken);
    }
    public void changeUserPassword(RegisteredUser user, String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        registeredUserRepository.save(user);
    }
    public PasswordResetToken getUserByPasswordResetToken(String token) {
        return passwordTokenRepository.findByToken(token);
    }
}