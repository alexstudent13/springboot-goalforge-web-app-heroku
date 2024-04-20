package project.sbtest.scope;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import project.sbtest.models.User;

import java.sql.Date;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserID(Long userID);
    void deleteByEmail(String email);

    // Custom query to find user by email using JPQL
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    // Custom query to find user by resetPasswordToken using JPQL
    @Query("SELECT u FROM User u WHERE u.resetPasswordToken = ?1")
    User findByResetPasswordToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.firstName = ?1, u.lastName = ?2 WHERE u.email = ?3")
    void updateUserInfoByEmail(String firstName, String lastName, String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.gender = ?1, u.dateOfBirth = ?2 WHERE u.email = ?3")
    void updateAdditionalInfoByEmail(String gender, Date dateOfBirth, String email);
}
