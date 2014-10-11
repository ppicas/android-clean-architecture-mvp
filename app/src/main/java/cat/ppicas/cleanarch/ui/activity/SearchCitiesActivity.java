package cat.ppicas.cleanarch.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import cat.ppicas.cleanarch.ui.fragment.PresenterHolderFragment;
import cat.ppicas.cleanarch.ui.fragment.SearchCitiesFragment;
import cat.ppicas.cleanarch.ui.presenter.Presenter;
import cat.ppicas.cleanarch.ui.view.View;

public class SearchCitiesActivity extends Activity implements PresenterHolder {

    private PresenterHolderFragment mPresenterHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(new PresenterHolderFragment(), null)
                    .add(android.R.id.content, new SearchCitiesFragment())
                    .commit();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof PresenterHolderFragment) {
            mPresenterHolder = (PresenterHolderFragment) fragment;
        }
    }

    @Override
    public <T extends Presenter<?>> T getPresenter(View<T> view) {
        return mPresenterHolder.getPresenter("presenter", view);
    }
}
