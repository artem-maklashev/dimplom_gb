package ru.artemmaklashev.authorization.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Класс пользователя, реализующий интерфейс UserDetails для использования в системе аутентификации и авторизации.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;

    /** Имя пользователя. */
    private String username;

    /** Адрес электронной почты пользователя. */
    private String email;

    /** Пароль пользователя. */
    private String password;

    /** Роль пользователя. */
    @Enumerated(EnumType.STRING)
    private Role role;

    /** Флаг, указывающий, подтвержден ли пользователь. */
    private boolean approved;

    /**
     * Получает коллекцию прав доступа пользователя.
     *
     * @return Коллекция прав доступа пользователя.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Получает имя пользователя (адрес электронной почты).
     *
     * @return Имя пользователя (адрес электронной почты).
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Проверяет, истек ли срок действия аккаунта.
     *
     * @return Всегда возвращает true.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Проверяет, заблокирован ли аккаунт пользователя.
     *
     * @return Всегда возвращает true.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Проверяет, истек ли срок действия учетных данных пользователя.
     *
     * @return Всегда возвращает true.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Проверяет, активен ли пользователь.
     *
     * @return Значение флага approved.
     */
    @Override
    public boolean isEnabled() {
        return approved;
    }
}

