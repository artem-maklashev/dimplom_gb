package ru.artemmaklashev.authorization.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью пользователь.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Находит пользователя по адресу электронной почты.
     *
     * @param email Адрес электронной почты для поиска.
     * @return Optional с найденным пользователем, если такой существует, иначе пустой Optional.
     */
    Optional<User> findByEmail(String email);
}
