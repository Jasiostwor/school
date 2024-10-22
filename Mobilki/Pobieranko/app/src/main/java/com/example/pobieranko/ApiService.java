package com.example.pobieranko;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("5TP/")
    Call<List<Message>> getMessages();
}
