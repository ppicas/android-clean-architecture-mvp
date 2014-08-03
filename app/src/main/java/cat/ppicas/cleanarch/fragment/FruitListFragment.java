package cat.ppicas.cleanarch.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import cat.ppicas.cleanarch.activity.PresenterFactory;
import cat.ppicas.cleanarch.activity.PresenterHolder;
import cat.ppicas.cleanarch.domain.Fruit;
import cat.ppicas.cleanarch.presenter.FruitListPresenter;
import cat.ppicas.cleanarch.presenter.view.FruitListView;

public class FruitListFragment extends Fragment
        implements PresenterFactory<FruitListPresenter>, FruitListView {

    private static final String TAG = FruitListFragment.class.getSimpleName();

    private FruitListPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = ((PresenterHolder) getActivity()).getPresenter(this);
        mPresenter.bindView(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.onShowFruits();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbindView();
    }

    @Override
    public FruitListPresenter createPresenter() {
        return new FruitListPresenter();
    }

    @Override
    public void showFruits(List<Fruit> fruits) {
        for (Fruit fruit : fruits) {
            Log.d(TAG, fruit.getName());
        }
    }

    @Override
    public void showProgress(boolean show) {
    }

    @Override
    public void showError(int stringResId) {
    }
}
