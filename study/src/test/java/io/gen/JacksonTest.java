package io.gen;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.gen.study.User;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JacksonTest {

    @Test
    public void test() throws Exception{
        User user = new User();
        user.setName("zhangsan");
        user.setEmail("www.gengelee@gmail.com");
        user.setAge(20);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(dateFormat.parse("1993-08-08"));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        System.out.println(json);

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user);
        String jsonlist = mapper.writeValueAsString(users);
        System.out.println(jsonlist);
    }

    @Test
    public void test2() throws Exception{
        String json = "{\"name\":\"zhangsan\",\"age\":20,\"birthday\":744739200000,\"email\":\"www.gengelee@gmail.com\"}";
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        System.out.println(user.toString());
    }

    @Test
    public void test3() throws Exception{
        User user = new User();
        user.setName("zhangsan");
        user.setEmail("www.gengelee@gmail.com");
        user.setAge(20);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(dateFormat.parse("1993-08-08"));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        System.out.println(json);
    }
}
