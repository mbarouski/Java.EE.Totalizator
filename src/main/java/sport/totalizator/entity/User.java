package sport.totalizator.entity;

import java.math.BigDecimal;

public class User {
    public static enum Role {
        ADMINISTRATOR, USER, MODERATOR
    }

    private int userId;
    private String login;
    private String passHash;
    private String email;
    private BigDecimal balance;
    private Role role;
    private boolean banned;

    public User() {
    }

    public User(int userId, String login, String passHash, String email, BigDecimal balance, Role role, boolean banned) {
        this.userId = userId;
        this.login = login;
        this.passHash = passHash;
        this.email = email;
        this.balance = balance;
        this.role = role;
        this.banned = banned;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (banned != user.banned) return false;
        if (!login.equals(user.login)) return false;
        if (!passHash.equals(user.passHash)) return false;
        if (!email.equals(user.email)) return false;
        if (!balance.equals(user.balance)) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + login.hashCode();
        result = 31 * result + passHash.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + balance.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + (banned ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", passHash='" + passHash + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", role=" + role +
                ", banned=" + banned +
                '}';
    }
}
