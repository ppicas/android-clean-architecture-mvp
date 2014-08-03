package cat.ppicas.cleanarch.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import cat.ppicas.cleanarch.fragment.FruitListFragment;
import cat.ppicas.cleanarch.fragment.PresenterHolderFragment;
import cat.ppicas.cleanarch.presenter.Presenter;

public class FruitListActivity extends Activity implements PresenterHolder {

    private PresenterHolderFragment mPresenterHolder;

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof PresenterHolderFragment) {
            mPresenterHolder = (PresenterHolderFragment) fragment;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(new PresenterHolderFragment(), null)
                    .add(android.R.id.content, new FruitListFragment())
                    .commit();
        }
    }

    @Override
    public <T extends Presenter<?>> T getPresenter(PresenterFactory<T> presenterFactory) {
        String tag = mPresenterHolder.createTag(presenterFactory);
        return mPresenterHolder.getPresenter(tag, presenterFactory);
    }
}
