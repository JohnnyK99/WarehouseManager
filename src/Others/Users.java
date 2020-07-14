package Others;

import java.util.HashMap;

public class Users {
    HashMap<String, String> credentials;

    public Users(){
        credentials = new HashMap<>();
    }

    public void addUser(String login, String password){
        credentials.put(login, password);
    }

    public HashMap<String, String> getCredentials(){
        return credentials;
    }
}
