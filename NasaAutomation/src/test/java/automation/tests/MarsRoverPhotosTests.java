package automation.tests;

import automation.clients.NasaAPIClient;
import automation.models.CuriosityCams;
import automation.models.MarsRoverPhoto;
import automation.models.MarsRoverPhotoResponse;
import automation.utils.PropertiesUtils;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class MarsRoverPhotosTests extends BaseTest {

    NasaAPIClient nasaAPIClient = new NasaAPIClient(PropertiesUtils.readProperty( "nasa_api_url"), PropertiesUtils.readProperty("api_key"));

    private Long sol = 1000L;
    private LocalDate earthDateFor1000Sol = LocalDate.of(2015,05,30);

    @Test
    public void getFirstTenPhotosOnSol1000Test(){
        MarsRoverPhotoResponse response = nasaAPIClient.getCuriosityPhotosBySol(sol);
        MarsRoverPhoto[] photos = response.getPhotos();
        Arrays.sort(photos, Comparator.comparing(MarsRoverPhoto::getId));
        MarsRoverPhoto[] firstTenPics = Arrays.copyOfRange(photos, 0, 10);
        int picsLength = firstTenPics.length;
        logger.info("Size of first Ten Pics array = {} ", picsLength);
        Assert.assertTrue(picsLength == 10);
        for(MarsRoverPhoto photo : firstTenPics){
            Assert.assertEquals(photo.getSol(), sol);
        }
    }

    @Test
    public void getFirstTenPhotosOnSol1000QueryingByEarthDateTest(){
        MarsRoverPhotoResponse response = nasaAPIClient.getCuriosityPhotosByEarthDate(earthDateFor1000Sol);
        MarsRoverPhoto[] photos = response.getPhotos();
        Arrays.sort(photos, Comparator.comparing(MarsRoverPhoto::getId));
        MarsRoverPhoto[] firstTenPics = Arrays.copyOfRange(photos, 0, 10);
        int picsLength = firstTenPics.length;
        logger.info("Size of first Ten Pics array = {} ", picsLength);
        Assert.assertTrue(picsLength == 10);
        for(MarsRoverPhoto photo : firstTenPics){
            Assert.assertEquals(photo.getEarth_date(), earthDateFor1000Sol);
        }
    }

    @Test
    public void comparePhotosTakenOnSol1000QueriedBySolAndByEarthDateTest(){
        MarsRoverPhotoResponse solResp = nasaAPIClient.getCuriosityPhotosBySol(sol);
        MarsRoverPhoto[] solPhotos = solResp.getPhotos();
        Arrays.sort(solPhotos, Comparator.comparing(MarsRoverPhoto::getId));
        MarsRoverPhoto[] firstTenSolPics = Arrays.copyOfRange(solPhotos, 0, 10);

        MarsRoverPhotoResponse earthDateResp = nasaAPIClient.getCuriosityPhotosByEarthDate(earthDateFor1000Sol);
        MarsRoverPhoto[] earthDatePhotos = earthDateResp.getPhotos();
        Arrays.sort(earthDatePhotos, Comparator.comparing(MarsRoverPhoto::getId));
        MarsRoverPhoto[] firstTenEarthDatePics = Arrays.copyOfRange(earthDatePhotos, 0, 10);

        for(int i = 0; i < firstTenSolPics.length; i++){
            logger.info("Photo Id queried by sol = {} , Photo Id queried by Earth date = {} ",
                    firstTenSolPics[i].getId(), firstTenEarthDatePics[i].getId());
            Assert.assertTrue(firstTenSolPics[i].equals(firstTenEarthDatePics[i]));
        }
    }

    /* This test will fail, since one of the cameras of the curiosity took more than 10 times the amount
        of pictures than each of the rest of the cameras did. */
    @Test
    public void validateNumberOfPhotosTakenByEachCameraOfTheCuriosity(){
        List<Integer> allPhotos = new ArrayList<>();
        List<CuriosityCams> cameraNames = new ArrayList<>();
        CuriosityCams[] cameras = CuriosityCams.values();

        for(CuriosityCams camera : cameras){
            allPhotos.add(nasaAPIClient.getCuriosityPhotosBySolAndCamera(sol, camera).getPhotos().length);
            cameraNames.add(camera);
        }

        for(int i = 0; i < allPhotos.size(); i++){
            int photo1 = allPhotos.get(i);
            CuriosityCams cam1 = cameraNames.get(i);
            if(photo1 == 0){
                logger.info("0 photos taken by = {} cam", cam1);
                continue;
            }
            for(int j = i+1; j < allPhotos.size(); j++){
                int photo2 = allPhotos.get(j);
                CuriosityCams cam2 = cameraNames.get(j);
                if(photo2 == 0){
                    logger.info("0 photos taken by = {} cam", cam2);
                    continue;
                }
                logger.info("{} photos taken by = {} cam, {} photos taken by = {} cam", photo1, cam1,  photo2, cam2);
                Assert.assertTrue(photo1 <= (photo2 *10 ) && photo2 <= (photo1 * 10));
            }
        }
    }

}
