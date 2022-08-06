package com.connection;

import retrofit.RestAdapter;

public class Connection {
    public static ConMethod getToken() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://logisticshortout.com/welcome")
//                .setConverter(GsonConverterFactory.create(gson))
                .build();

        return adapter.create(ConMethod.class);
    }

}
