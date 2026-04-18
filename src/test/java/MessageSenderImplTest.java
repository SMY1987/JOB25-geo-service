import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MessageSenderImplTest.MockitoExtension.class)
public class MessageSenderImplTest {

    @Mock
    private GeoServiceImpl geoService;

    @Mock
    private LocalizationServiceImpl localizationService;

    @InjectMocks
    private MessageSenderImpl messageSender;

    @Test
    void testSendMessage_RussianIp() {
        String ip = "172.16.0.1";
        when(geoService.byIp(ip)).thenReturn(new Location(Country.RUSSIA));
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Привет!");

        String result = messageSender.send(ip);

        assertEquals("Привет!", result);
        verify(geoService, times(1)).byIp(ip);
        verify(localizationService, times(1)).locale(Country.RUSSIA);
    }

    @Test
    void testSendMessage_AmericanIp() {
        String ip = "96.16.0.1";
        when(geoService.byIp(ip)).thenReturn(new Location(Country.USA));
        when(localizationService.locale(Country.USA)).thenReturn("Hello!");

        String result = messageSender.send(ip);

        assertEquals("Hello!", result);
        verify(geoService, times(1)).byIp(ip);
        verify(localizationService, times(1)).locale(Country.USA);
    }

    protected class MockitoExtension implements Extension {
    }
}
