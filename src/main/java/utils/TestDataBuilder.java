package utils;

import pojo.AddPlace;
import pojo.Location;
import pojo.UpdatePlace;

import java.util.Arrays;

public class TestDataBuilder {

    public AddPlace addPlacePayload(String address, double lat, double lng) {
        AddPlace addPlace = new AddPlace();
        Location location = new Location();
        location.setLat(lat);
        location.setLng(lng);
        addPlace.setLocation(location);
        addPlace.setAccuracy(50);
        addPlace.setName("Frontline house");
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress(address);
        addPlace.setTypes(Arrays.asList("shoe park", "shop"));
        addPlace.setWebsite("http://google.com");
        addPlace.setLanguage("French-IN");
        return addPlace;
    }

    public UpdatePlace updatePlacePayload(String placeId, String newAddress) {
        UpdatePlace updatePlace = new UpdatePlace();
        updatePlace.setPlace_id(placeId);
        updatePlace.setAddress(newAddress);
        updatePlace.setKey("qaclick123");
        return updatePlace;
    }
}
