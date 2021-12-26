package lesson20;

import com.google.gson.Gson;
import testdata.LoginCreds;

import java.io.BufferedReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataObjectBuilder {

    public static <T> T buildDataObject(String filePath, Class <T> dataType) {
        String absolutePath = System.getProperty("user.dir") + filePath;
        try (Reader reader = Files.newBufferedReader(Paths.get(absolutePath))){
            //create a Gson object
            Gson gson = new Gson();

            //convert json data to a specific type
             return gson.fromJson(reader, dataType);
        }catch (Exception e){

        }
        return null;

    }

    public static void main(String[] args) {
//        String jsonLocation = "/src/test/java/testdata/loginCreds.json";
//        LoginCreds[] loginCreds = DataObjectBuilder.buildLoginCreds(jsonLocation, );
//        for (LoginCreds loginCred : loginCreds) {
//            System.out.println(loginCred);
//        }
    }
}
