
package News_alihan;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class Apache {
    //Global değişkenler
     public String apiUrl,resultJson;
  
   
   public Apache(String url){
       //Nesne tanımlanırken url alınıyor.
       this.apiUrl = url;
   }
   
   
   public String getRequest() throws IOException{
       //Get isteği atma fonksiyonu
       HttpGet  request = new HttpGet(this.apiUrl);
       
      //Header bileşenleri ekleniyor.
       request.addHeader("accept","application/json");
       request.addHeader(HttpHeaders.USER_AGENT,"Googlebot");
       
    try(CloseableHttpClient httpClient= HttpClients.createDefault();
          
            CloseableHttpResponse response = httpClient.execute(request)){
       
        HttpEntity entity = response.getEntity();
        if(entity != null){
           
            String result = EntityUtils.toString(entity);
           
            //Gelen json değeri string değişkene aktarılıyor.
            resultJson = result;
        }
       
    }
       
    
  
       
       
     return resultJson;
   }
   
    
}
