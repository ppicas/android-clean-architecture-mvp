package cat.ppicas.cleanarch.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import cat.ppicas.cleanarch.ui.activity.PresenterHolder;
import cat.ppicas.cleanarch.ui.presenter.SearchCitiesPresenter;
import cat.ppicas.cleanarch.ui.view.SearchCitiesView;

public class SearchCitiesFragment extends Fragment implements SearchCitiesView {

    private SearchCitiesPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = ((PresenterHolder) getActivity()).getPresenter(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.bindView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbindView();
    }

    @Override
    public SearchCitiesPresenter createPresenter() {
        return new SearchCitiesPresenter();
    }

    @Override
    public void showProgress(boolean show) {
    }

    @Override
    public void showError(int stringResId) {
    }
}
