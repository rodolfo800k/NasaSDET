package automation.clients;

import automation.models.CuriosityCams;
import automation.models.MarsRoverPhotoResponse;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


/* This class class handles all the requests made to the Nasa public API. RestTemplate is used to
perform the requests, and the responses are being parsed to model classes which contain the expected
response attributes.
 */
public class NasaAPIClient {

     RestTemplate restTemplate = new RestTemplate();

    public NasaAPIClient(String apiUrl, String apiKey){
        this.nasaApiUrl = apiUrl;
        this.apiKeyValue = apiKey;
    }

    private String nasaApiUrl ;

    private String apiKeyValue;

    private String curiosityPhotosPath = "rovers/curiosity/photos";

    private String apiKey= "api_key=";

    private String params;

    public MarsRoverPhotoResponse getCuriosityPhotosBySol(long sol){
        params = "?sol=" + sol + "&";
        return restTemplate.getForObject(nasaApiUrl + curiosityPhotosPath + params + apiKey + apiKeyValue,
               MarsRoverPhotoResponse.class);
    }

    public MarsRoverPhotoResponse getCuriosityPhotosByEarthDate(LocalDate earthDate){
        params = "?earth_date=";
        return restTemplate.getForObject(nasaApiUrl + curiosityPhotosPath + params + earthDate + "&" +  apiKey + apiKeyValue,
                MarsRoverPhotoResponse.class);
    }

    public MarsRoverPhotoResponse getCuriosityPhotosBySolAndCamera(long sol, CuriosityCams camera){
        params = "?sol=" + sol + "&camera=" + camera.name().toLowerCase();
        return restTemplate.getForObject(nasaApiUrl + curiosityPhotosPath + params + "&" +  apiKey + apiKeyValue,
                MarsRoverPhotoResponse.class);
    }

}
