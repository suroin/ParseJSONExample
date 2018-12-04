import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.HttpClient;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.http.util.EntityUtils;

import org.openqa.selenium.WebDriver;

import java.io.*;
import java.lang.Exception;
import java.text.ParseException;
import com.jayway.jsonpath.JsonPath;

import java.util.*;


import static java.lang.System.out;
import static java.lang.System.setOut;


public class SerilizeableJsonExample {

    // public SerilizeableJsonExample(WebDriver driver) { super(driver); }

    public static final String statictoken = "";

    private static final String USER_AGENT = "Chrome/70.0.3538.77";

    private static final String GET_URL = "http://website.ineco.dev.sflpro.com/api/page/deposits/?locale=hy&segment=Individual";

    public static void main(String[] args) throws Exception {

        HttpResponse response = Perform_GET(GET_URL);
        out.println("---   " +  response.getStatusLine().getStatusCode());


        getDepositArguments();

    }



    private static  HttpResponse Perform_GET(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Accept", "application/json");
        HttpClient httpClient = HttpClientBuilder.create().build();
        return httpClient.execute(httpGet);
    }

    private static  HttpResponse Perform_Post(String url, String requestBody) throws ParseException, IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Accept", "application/json");
        StringEntity stringEntity = new StringEntity(requestBody);
        httpPost.setEntity(stringEntity);
        HttpClient httpClient = HttpClientBuilder.create().build();
        return httpClient.execute(httpPost);
    }

    public static void  getDepositArguments() throws Exception {
        HashMap<String,Object> hashM = new HashMap<>();
        HttpResponse response = Perform_GET(GET_URL);
        String strResponse = EntityUtils.toString(response.getEntity());
        JSONArray jsonArr = JsonPath.read(strResponse,"$.sections[*].deposits[*].Deposit-Depositsetting[*].Depositsetting-Depositterm[0]");


        ObjectMapper mapper = new ObjectMapper();
        JSONObject jobj= null;

        for(int i = 0; i < jsonArr.size(); ++i) {
            hashM = (HashMap<String, Object>) jsonArr.get(i);
            jobj = new JSONObject(hashM);
            DepositValues values1 = mapper.readValue(jobj.toJSONString(), DepositValues.class);
            System.out.println(values1);

        }

        DepositValues value = new DepositValues(4.5,5.7, 6.0, 8.0,
                0.7, "deposit");

        testSerializingWithJsonValue(value);


    }

    @JsonValue
    public static void testSerializingWithJsonValue(DepositValues val) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(val);
        System.out.println(jsonString);

    }




}

