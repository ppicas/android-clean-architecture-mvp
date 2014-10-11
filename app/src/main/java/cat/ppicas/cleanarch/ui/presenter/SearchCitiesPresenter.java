package cat.ppicas.cleanarch.ui.presenter;

import android.os.Bundle;

import cat.ppicas.cleanarch.ui.view.SearchCitiesView;

public class SearchCitiesPresenter extends Presenter<SearchCitiesView> {

    @Override
    public void saveState(Bundle state) {
    }

    @Override
    public void restoreState(Bundle state) {
    }

    @Override
    protected Class<SearchCitiesView> getViewClassToken() {
        return SearchCitiesView.class;
    }
}
