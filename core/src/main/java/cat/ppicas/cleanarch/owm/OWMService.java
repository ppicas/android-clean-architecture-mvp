package cat.ppicas.cleanarch.owm;

import cat.ppicas.cleanarch.owm.model.OWMCurrentWeather;
import cat.ppicas.cleanarch.owm.model.OWMCurrentWeatherList;
import cat.ppicas.cleanarch.owm.model.OWMDailyForecastList;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface OWMService {

    @GET("/data/2.5/find?units=metric")
    @Headers("Cache-Control: max-age=900, max-stale=3600")
    public OWMCurrentWeatherList getCurrentWeatherByCityName(@Query("q") String query);

    @GET("/data/2.5/weather?units=metric")
    @Headers("Cache-Control: max-age=900, max-stale=3600")
    public OWMCurrentWeather getCurrentWeatherByCityId(@Query("id") String id);

    @GET("/data/2.5/forecast/daily?units=metric")
    @Headers("Cache-Control: max-age=900, max-stale=3600")
    public OWMDailyForecastList getDailyForecastByCityId(@Query("id") String id,
            @Query("cnt") int days);
}
