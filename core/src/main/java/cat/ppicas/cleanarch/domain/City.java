package cat.ppicas.cleanarch.domain;

public class City {

    private String mId;
    private String mName;
    private String mCountry;
    private CurrentWeather mCurrentWeather;

    public City(String id, String name, String country) {
        mId = id;
        mName = name;
        mCountry = country;
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

    public void setCurrentWeather(CurrentWeather currentWeather) {
        mCurrentWeather = currentWeather;
    }

    public CurrentWeather getCurrentWeather() {
        return mCurrentWeather;
    }
}
