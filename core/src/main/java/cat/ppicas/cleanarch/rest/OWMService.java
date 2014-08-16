package cat.ppicas.cleanarch.rest;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface OWMService {

    @GET("/data/2.5/find?units=metric")
    @Headers("Cache-Control: max-age=900, max-stale=3600")
    public CityWeatherList findCitiesWeather(@Query("q") String query);

    @GET("http://api.openweathermap.org/data/2.5/find?units=metric")
    @Headers("Cache-Control: max-age=0")
    public CityWeatherList findCitiesWeatherSkipCache(@Query("q") String query);
}
