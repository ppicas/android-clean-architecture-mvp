/*
 * Copyright (C) 2015 Pau Picas Sans <pau.picas@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package cat.ppicas.cleanarch.app;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cat.ppicas.cleanarch.owm.OWMCurrentWeatherRepository;
import cat.ppicas.cleanarch.owm.OWMDailyForecastRepository;
import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.cleanarch.owm.OWMCityRepository;
import cat.ppicas.cleanarch.owm.OWMService;
import cat.ppicas.cleanarch.repository.CurrentWeatherRepository;
import cat.ppicas.cleanarch.repository.DailyForecastRepository;
import cat.ppicas.cleanarch.util.AsyncTaskExecutor;
import cat.ppicas.framework.task.TaskExecutor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Implementation of {@link ServiceContainer} interface exposing a default configuration
 * for the app.
 */
class DefaultServiceContainer implements ServiceContainer {

    private Context mContext;

    private OWMService mOWMService;
    private RestAdapter mRestAdapter;
    private OkHttpClient mOkClient;
    private AsyncTaskExecutor mTaskExecutor;

    public DefaultServiceContainer(Context context) {
        mContext = context.getApplicationContext();
    }

    @Override
    public CityRepository getCityRepository() {
        return new OWMCityRepository(getOWMService());
    }

    @Override
    public CurrentWeatherRepository getCurrentWeatherRepository() {
        return new OWMCurrentWeatherRepository(getOWMService());
    }

    @Override
    public DailyForecastRepository getDailyForecastRepository() {
        return new OWMDailyForecastRepository(getOWMService());
    }

    @Override
    public TaskExecutor getTaskExecutor() {
        if (mTaskExecutor == null) {
            mTaskExecutor = new AsyncTaskExecutor();
        }
        return mTaskExecutor;
    }

    private OWMService getOWMService() {
        if (mOWMService == null) {
            mOWMService = getRestAdapter().create(OWMService.class);
        }
        return mOWMService;
    }

    private RestAdapter getRestAdapter() {
        if (mRestAdapter == null) {
            mRestAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://api.openweathermap.org")
                    .setClient(new OkClient(getOkHttpClient()))
                    .setLogLevel(RestAdapter.LogLevel.NONE)
                    .build();
        }

        return mRestAdapter;
    }

    private OkHttpClient getOkHttpClient() {
        if (mOkClient == null) {
            mOkClient = new OkHttpClient();
            mOkClient.setConnectTimeout(10, TimeUnit.SECONDS);
            mOkClient.setReadTimeout(5, TimeUnit.SECONDS);
            mOkClient.setWriteTimeout(5, TimeUnit.SECONDS);
            mOkClient.setCache(createOkHttpCache());
        }

        return mOkClient;
    }

    private Cache createOkHttpCache() {
        try {
            File directory = new File(mContext.getCacheDir(), "ok-http");
            return new Cache(directory, 3000000);
        } catch (IOException e) {
            return null;
        }
    }
}
