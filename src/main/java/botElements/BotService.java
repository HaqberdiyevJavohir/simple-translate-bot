package botElements;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.TranslateModel.Translate;
import model.User;
import model.UserState;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class BotService implements BotUtils{
    public synchronized Translate getTranslate(String text, String lang){
        Translate translate = new Translate();
        URL url = null;
        try {
            url = new URL("https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=" + key + "&lang=" + lang + "&text=" + text);
            URLConnection urlConnection = url.openConnection();
            ObjectMapper objectMapper=new ObjectMapper();
            translate=objectMapper.readValue(urlConnection.getInputStream(),Translate.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return translate;
    }

    public void write(List<User> userList) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("src/main/java/files/userList.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, userList);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Writing error " + e.toString());
        }
    }

    public User read(long userId){
        List<User> list = read();
        for (User user : list) {
            if (userId == user.getUserId()){
                return user;
            }
        }
        return null;
    }

    public synchronized void add(User user) {
        List<User> userList = read();
        userList.add(user);
        write(userList);
    }

    public List<User> read() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> list = new ArrayList<>();
        try {
            File file = new File("src/main/java/files/userList.json");
            list = objectMapper.readValue(file, new TypeReference<ArrayList<User>>() {
            });
            return list;
        } catch (Exception | NoClassDefFoundError e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setUserSate(User user, UserState userState){
        List<User> userList = read();
        List<User> users = new ArrayList<>();
        for (User user1 : userList) {
            if (user1.equals(user)){
                User user2 = new User();
                user2.setUserId(user.getUserId());
                user2.setName(user.getName());
                user2.setUserState(userState);
                users.add(user2);
            }else{
                users.add(user1);
            }
        }
        write(users);
    }
}
