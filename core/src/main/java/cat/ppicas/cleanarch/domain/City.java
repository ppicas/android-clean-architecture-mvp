package cat.ppicas.cleanarch.domain;

public class City {

    private String mId;
    private String mName;
    private String mCountry;
    private CurrentWeatherPreview mCurrentWeatherPreview;

    public City(String id, String name, String country) {
        mId = id;
        mName = name;
        mCountry = country;
    }

    public City(String id, String name, String country,
            CurrentWeatherPreview currentWeatherPreview) {
        mId = id;
        mName = name;
        mCountry = country;
        mCurrentWeatherPreview = currentWeatherPreview;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getCountry() {
        return mCountry;
    }

    public CurrentWeatherPreview getCurrentWeatherPreview() {
        return mCurrentWeatherPreview;
    }
}
