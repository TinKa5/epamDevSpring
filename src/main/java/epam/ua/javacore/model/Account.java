package epam.ua.javacore.model;

public class Account extends Entity {
    String content;
    AccountStatus accountStatus;

    public Account(){};

    public Account(String content, AccountStatus accountStatus) {
        this.content = content;
        this.accountStatus = accountStatus;
    }

    public String getContent() {
        return content;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }


    @Override
    public String toString() {
        return Long.toString(getId())+" "+content+" "+accountStatus;
    }
}
