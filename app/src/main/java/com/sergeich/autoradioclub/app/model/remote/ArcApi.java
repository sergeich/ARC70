package com.sergeich.autoradioclub.app.model.remote;

import com.sergeich.autoradioclub.app.model.ArcResponse;
import com.sergeich.autoradioclub.app.model.Message;
import com.sergeich.autoradioclub.app.model.QueryDate;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ArcApi {

    @GET("/archive.php")
    Observable<ArcResponse<List<Message>>> messagesByDate(@Query("date") QueryDate date);

}

