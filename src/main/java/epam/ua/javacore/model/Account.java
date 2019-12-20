package epam.ua.javacore.model;

import epam.ua.javacore.util.ModelUtil;
import epam.ua.javacore.repository.Connector;

import static epam.ua.javacore.util.ModelUtil.toLong;

public class Account extends Entity {
    String content;
    AccountStatus accountStatus;

    public Account(String fileString, Connector connector){
        super(fileString,connector);
    }

    public String serialize() {
        return super.id.toString()+" "+content+" "+accountStatus.toString();
    }

    public void deserialize(String string, Connector connector) {
        String[] strings=string.split(" ");
        super.id= toLong(strings[0]);
        accountStatus=AccountStatus.valueOf(strings[1]);
        connector.registration(this);

    }
}
