package cat.ppicas.cleanarch.ui.activity;

import cat.ppicas.cleanarch.domain.City;

/**
 * Interface that can be used to open (launch) different activities of the app without
 * relying on a concrete implementation. This interface helps to decouple the presenters
 * from the activities.
 */
public interface ActivityNavigator {

    /**
     * Opens the details activity for the specified {@link City}.
     *
     * @param cityId an ID of the desired {@code City} to open
     */
    public void openCityDetails(String cityId);

}
