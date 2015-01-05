package sample;
import java.io.File;
import java.io.IOException;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;


public class GetLocation {


    public ServerLocation getLocation(String ipAddress,String subip) {

        File file = new File(
                "GeoLiteCity.dat");
        return getLocation(ipAddress, file,subip);

    }

    public ServerLocation getLocation(String ipAddress, File file,String subip) {

        ServerLocation serverLocation = null;

        try {

            serverLocation = new ServerLocation();

            LookupService lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
            Location locationServices = lookup.getLocation(ipAddress);

//            serverLocation.setCountryCode(locationServices.countryCode);
//            serverLocation.setCountryName(locationServices.countryName);
//            serverLocation.setRegion(locationServices.region);
//            serverLocation.setRegionName(regionName.regionNameByCode(
//                    locationServices.countryCode, locationServices.region));
//            serverLocation.setCity(locationServices.city);
//            serverLocation.setPostalCode(locationServices.postalCode);
//            serverLocation.setLatitude(String.valueOf(locationServices.latitude));
//            serverLocation.setLongitude(String.valueOf(locationServices.longitude));
            new FindLocation(String.valueOf(locationServices.latitude),String.valueOf(locationServices.longitude),ipAddress,subip);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return serverLocation;
    }
}
