package epam.ua.javacore.model;

import epam.ua.javacore.repository.io.Connector;

import static epam.ua.javacore.util.ModelUtil.toLong;

public class Account extends Entity {
    String content;
    AccountStatus accountStatus;

    public Account(String fileString, Connector connector){
        super(fileString,connector);
    }

    public Account(){}

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

    public String serialize() {
        return content+" "+accountStatus.toString();
    }

    public void deserialize(String string, Connector connector) {
        String[] strings=string.split(" ");
        super.id= toLong(strings[0]);
        content=strings[1];
        accountStatus=AccountStatus.valueOf(strings[2]);
        connector.registration(this);

    }

    @Override
    public String toString() {
        return Long.toString(getId())+" "+content+" "+accountStatus;
    }
}
