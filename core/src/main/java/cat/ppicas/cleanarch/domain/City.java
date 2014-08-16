package cat.ppicas.cleanarch.domain;

public class City {

    private String mId;
    private String mName;
    private CurrentWeather mCurrentWeather;

    public City(String id, String name) {
        mId = id;
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        mCurrentWeather = currentWeather;
    }

    public CurrentWeather getCurrentWeather() {
        return mCurrentWeather;
    }
}
