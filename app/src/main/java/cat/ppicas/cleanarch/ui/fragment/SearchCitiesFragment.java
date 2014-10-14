package cat.ppicas.cleanarch.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.app.App;
import cat.ppicas.cleanarch.app.ServiceContainer;
import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.ui.activity.PresenterHolder;
import cat.ppicas.cleanarch.ui.adapter.CityAdapter;
import cat.ppicas.cleanarch.ui.presenter.SearchCitiesPresenter;
import cat.ppicas.cleanarch.ui.view.SearchCitiesView;

public class SearchCitiesFragment extends Fragment implements SearchCitiesView {

    private SearchCitiesPresenter mPresenter;

    private CityAdapter mAdapter;

    private SearchView mSearch;
    private ListView mList;
    private ProgressBar mProgress;
    private TextView mEmpty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = ((PresenterHolder) getActivity()).getOrCreatePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        final View view = inflater.inflate(R.layout.fragment_search_cities, container, false);

        bindViews(view);

        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(view.getWindowToken(), 0);
                mPresenter.onCitySearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mAdapter = new CityAdapter(getActivity());
        mList.setAdapter(mAdapter);

        mProgress.setVisibility(View.GONE);
        mEmpty.setVisibility(View.GONE);

        mPresenter.bindView(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onViewStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onViewStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbindView();
    }

    @Override
    public SearchCitiesPresenter createPresenter() {
        ServiceContainer sc = App.getServiceContainer();
        return new SearchCitiesPresenter(sc.getTaskExecutor(), sc.getCityRepository());
    }

    @Override
    public void showCities(List<City> cities) {
        mAdapter.clear();
        if (cities.isEmpty()) {
            mEmpty.setVisibility(View.VISIBLE);
        } else {
            mEmpty.setVisibility(View.GONE);
            mAdapter.addAll(cities);
        }
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            mList.setVisibility(View.GONE);
            mEmpty.setVisibility(View.GONE);
            mProgress.setVisibility(View.VISIBLE);
        } else {
            mProgress.setVisibility(View.GONE);
            mList.setVisibility(mAdapter.isEmpty() ? View.GONE : View.VISIBLE);
            mEmpty.setVisibility(mAdapter.isEmpty() ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void showError(int stringResId) {
        Toast.makeText(getActivity(), stringResId, Toast.LENGTH_LONG).show();
    }

    private void bindViews(View view) {
        mSearch = (SearchView) view.findViewById(R.id.search_cities__search);
        mList = (ListView) view.findViewById(R.id.search_cities__list);
        mProgress = (ProgressBar) view.findViewById(R.id.search_cities__progress);
        mEmpty = (TextView) view.findViewById(R.id.search_cities__empty);
    }
}
