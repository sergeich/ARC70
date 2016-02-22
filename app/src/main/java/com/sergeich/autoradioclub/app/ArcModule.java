package com.sergeich.autoradioclub.app;


import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.sergeich.autoradioclub.app.model.DbOpenHelper;
import com.sergeich.autoradioclub.app.model.Message;
import com.sergeich.autoradioclub.app.model.MessageStorIOSQLiteDeleteResolver;
import com.sergeich.autoradioclub.app.model.MessageStorIOSQLiteGetResolver;
import com.sergeich.autoradioclub.app.model.MessageStorIOSQLitePutResolver;
import com.sergeich.autoradioclub.app.model.remote.ArcApi;
import com.sergeich.autoradioclub.app.model.remote.jackson.JacksonCsvConverterFactory;
import com.sergeich.autoradioclub.messages.MessagesModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


@Module(includes = {
        MessagesModule.class
})
public class ArcModule {

    public static final String SERVICE_BASE_URL = "http://radio70.ru";

    private static final String TAG = ArcModule.class.getName();

    private final Context context;

    public ArcModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    StorIOSQLite storIoSqLite() {

        DbOpenHelper dbOpenHelper = new DbOpenHelper(context);

        return DefaultStorIOSQLite
                .builder()
                .sqliteOpenHelper(dbOpenHelper)
                .addTypeMapping(
                        Message.class,
                        SQLiteTypeMapping.<Message>builder()
                                .putResolver(new MessageStorIOSQLitePutResolver())
                                .getResolver(new MessageStorIOSQLiteGetResolver())
                                .deleteResolver(new MessageStorIOSQLiteDeleteResolver())
                                .build()
                )
                .build();

    }

    @Provides
    @Singleton
    ArcApi radio70Api() {

        CsvMapper csvMapper = new CsvMapper();

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {

                    Request request = chain.request();
                    HttpUrl httpUrl = request
                            .url()
                            .newBuilder()
                            .build();
                    request = request.newBuilder().url(httpUrl).build();

                    Log.i(TAG, request.toString());

                    return chain.proceed(request);

                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonCsvConverterFactory.create(csvMapper))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(SERVICE_BASE_URL)
                .client(httpClient)
                .build();

        return retrofit.create(ArcApi.class);

    }

}
