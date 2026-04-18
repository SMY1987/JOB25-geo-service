import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoServiceImplTest {

    @Test
    void testRussianIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("172.16.0.1");
        assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    void testAmericanIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("96.16.0.1");
        assertEquals(Country.USA, location.getCountry());
    }

    @Test
    void testOtherCountryIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = geoService.byIp("8.8.8.8");
        assertEquals(Country.USA, location.getCountry()); // По умолчанию США
    }
}