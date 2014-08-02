package cat.ppicas.cleanarch.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import cat.ppicas.cleanarch.domain.Fruit;
import cat.ppicas.cleanarch.presenter.FruitListPresenter;
import cat.ppicas.cleanarch.presenter.view.FruitListView;

public class FruitListFragment extends Fragment implements FruitListView {

    private static final String TAG = FruitListFragment.class.getSimpleName();

    private static final String PRESENTER_TAG = FruitListPresenter.class.getName();

    private PresenterHolderFragment<FruitListPresenter> mHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PresenterFactory<FruitListPresenter> factory = new PresenterFactory<FruitListPresenter>() {
            @Override
            public FruitListPresenter create() {
                return new FruitListPresenter();
            }
        };

        mHolder = PresenterHolderFragment.findOrCreate(
                getFragmentManager(), PRESENTER_TAG, factory, (FruitListView) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isRemoving()) {
            mHolder.remove();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHolder.getPresenter().onShowFruits();
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
