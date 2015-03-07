package cat.ppicas.cleanarch.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.app.ServiceContainerProvider;
import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.ui.display.CityListItemDisplay;
import cat.ppicas.cleanarch.ui.presenter.CityListItemPresenter;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class CityAdapter extends ArrayAdapter<City> {

    private final LayoutInflater mInflater;
    private final TaskExecutor mTaskExecutor;

    public CityAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
        mInflater = LayoutInflater.from(context);

        Context app = getContext().getApplicationContext();
        ServiceContainerProvider provider = (ServiceContainerProvider) app;
        mTaskExecutor = provider.getServiceContainer().getTaskExecutor();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = mInflater.inflate(R.layout.view_city_list_item, parent, false);
        }
        CityListItemDisplay display = (CityListItemDisplay) view;

        City city = getItem(position);

        // Initialize or reconfigures a Presenter for CityListItemView
        CityListItemPresenter presenter = (CityListItemPresenter) view.getTag();
        if (presenter == null) {
            presenter = new CityListItemPresenter(mTaskExecutor, city);
            presenter.bindDisplay(display);
            view.setTag(presenter);
            view.addOnAttachStateChangeListener(new AdapterViewUnbinder(presenter));
        } else {
            presenter.setCity(city);
        }

        return view;
    }
}
