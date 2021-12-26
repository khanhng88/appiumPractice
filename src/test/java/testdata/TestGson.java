package testdata;

import com.google.gson.Gson;

public class TestGson {

    public static void main(String[] args) {
        String jsonObject = "{\n" +
                "  \"userName\": \"teo@sth.com\",\n" +
                "  \"password\": \"87654321\"\n" +
                "}";
        Gson gson = new Gson();
        LoginCreds loginCreds = gson.fromJson(jsonObject , LoginCreds.class );
        System.out.println(loginCreds);
        System.out.println(gson.toJson(loginCreds));
    }
}
