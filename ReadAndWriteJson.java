import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Copyright 2020 (C) fwutech.com
 *
 * @author : Esmaeil Sadeghi {@link "mailto:e.sadeghi@fwutech.com"}
 */
public class ReadAndWriteJson {

    private static final String fileName = "../files/user.json";
    private ClassLoader classLoader = getClass().getClassLoader();
    private URL resource = classLoader.getResource(fileName);


    public static void main(String[] args) throws IOException {
//        System.out.println(findAllUser());
//        System.out.println(findById(3));



//        User user = new User();
//        user.setId(5);
//        user.setPhoneNo("09103977079");
//        user.setUsername("asghar");
//        user.setPassword("aaa");
//        save(user); //
    }

    public static List<User> findAllUser() throws IOException {

        Reader reader = Files.newBufferedReader(Paths.get(fileName));
        List<User> users = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());
        reader.close();
        return users;
    }

    public static User findById(int id) throws IOException {
        List<User> users = findAllUser();
        for (User user: users) {
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public static void save(User user) throws IOException {
        List<User> users = findAllUser();
        users.add(user);
        PrintWriter printWriter = new PrintWriter(fileName);
        printWriter.print("");
        printWriter.close();
        Writer writer = new FileWriter(fileName, true);
        new Gson().toJson(users, writer);
        writer.close();
    }
}
