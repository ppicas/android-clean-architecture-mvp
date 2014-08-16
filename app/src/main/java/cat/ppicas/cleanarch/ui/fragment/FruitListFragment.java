package cat.ppicas.cleanarch.ui.fragment;

import android.app.Fragment;

public class FruitListFragment extends Fragment
        /*implements PresenterFactory<FruitListPresenter>, FruitListView*/ {

    /*private static final String TAG = FruitListFragment.class.getSimpleName();

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
    }*/
}
