package epam.ua.javacore.model;

import epam.ua.javacore.repository.io.Connector;

import static epam.ua.javacore.util.ModelUtil.toLong;

public class Account extends Entity {
    String content;
    AccountStatus accountStatus;

    public Account(String fileString, Connector connector){
        super(fileString,connector);
    }

    public Account(String content, AccountStatus accountStatus){
        this.content=content;
        this.accountStatus=accountStatus;
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
