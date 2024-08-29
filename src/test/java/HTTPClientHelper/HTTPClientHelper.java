package HTTPCLientHelper;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.util.HashMap;
import java.util.Map;

public class HTTPClientHelper {

    public static Map <String,String> resultContent;

    public static Map <String,String> submitGetRequestAndGetResponse(String url) {

        resultContent = new HashMap<>();
        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);

        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            try(CloseableHttpResponse httpResponse = httpClient.execute(httpGet)){

                System.out.println(httpResponse.getCode());
                System.out.print(httpResponse.getVersion());
                System.out.println(httpResponse.getReasonPhrase());
                HttpEntity entity = httpResponse.getEntity();

                resultContent.put("ResponseCode", String.valueOf(httpResponse.getCode()));
                resultContent.put("ResponseBody", EntityUtils.toString(entity));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return resultContent;
    }

    public static Map <String,String> submitPostRequestAndPostResponse(String endPointUrl,String requestBody) {

        resultContent = new HashMap<>();
        System.out.println(endPointUrl);
        HttpPost httpPost = new HttpPost(endPointUrl);
        StringEntity stringEntity = new StringEntity(requestBody);
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            try(CloseableHttpResponse httpResponse = httpClient.execute(httpPost)){

                System.out.println(httpResponse.getCode());
                System.out.print(httpResponse.getVersion());
                System.out.println(httpResponse.getReasonPhrase());
                HttpEntity entity = httpResponse.getEntity();

                resultContent.put("ResponseCode", String.valueOf(httpResponse.getCode()));
                resultContent.put("ResponseBody", EntityUtils.toString(entity));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return resultContent;
    }
}
