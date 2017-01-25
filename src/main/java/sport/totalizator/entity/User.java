package sport.totalizator.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * {@link User} represents entity of user in database.
 */
public class User {
    public static enum Role {
        ADMINISTRATOR("ADMINISTRATOR"), USER("USER"), MODERATOR("MODERATOR");

        private String value;

        private Role(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

    private int userId;
    private String login;
    private String passHash;
    private String email;
    private BigDecimal balance;
    private Role role;
    private boolean banned;
    private List<Rate> activeRates;
    private List<Rate> finishedRates;

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

    public List<Rate> getActiveRates() {
        return activeRates;
    }

    public void setActiveRates(List<Rate> activeRates) {
        this.activeRates = activeRates;
    }

    public List<Rate> getFinishedRates() {
        return finishedRates;
    }

    public void setFinishedRates(List<Rate> finishedRates) {
        this.finishedRates = finishedRates;
    }


}
