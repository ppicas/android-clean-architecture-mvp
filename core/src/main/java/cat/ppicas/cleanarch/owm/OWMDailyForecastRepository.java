package cat.ppicas.cleanarch.owm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cat.ppicas.cleanarch.domain.DailyForecast;
import cat.ppicas.cleanarch.owm.model.OWMDailyForecast;
import cat.ppicas.cleanarch.owm.model.OWMDailyForecastList;
import cat.ppicas.cleanarch.repository.DailyForecastRepository;

public class OWMDailyForecastRepository implements DailyForecastRepository {

    private static final int FORECAST_DAYS = 3;

    private OWMService mService;

    public OWMDailyForecastRepository(OWMService service) {
        mService = service;
    }

    @Override
    public List<DailyForecast> getDailyForecasts(String cityId) {
        List<DailyForecast> list = new ArrayList<DailyForecast>();
        OWMDailyForecastList owmDFList = mService.getDailyForecastByCityId(cityId, FORECAST_DAYS);
        for (OWMDailyForecast owmDF : owmDFList.getList()) {
            list.add(createDailyForecast(cityId, owmDF));
        }
        return list;
    }

    private DailyForecast createDailyForecast(String cityId, OWMDailyForecast owmDF) {
        Date data = new Date(owmDF.getTimestamp() * 1000L);
        String description = (owmDF.getWeatherList().length >= 1)
                ? owmDF.getWeatherList()[0].getDescription() : "Not available";
        OWMDailyForecast.Temp temp = owmDF.getTemp();
        return new DailyForecast(cityId, data, description, temp.getDay(), temp.getMin(),
                temp.getMax(), owmDF.getHumidity(), owmDF.getWindSpeed());
    }
}
