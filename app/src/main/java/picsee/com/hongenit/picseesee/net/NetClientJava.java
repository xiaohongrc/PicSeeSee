package picsee.com.hongenit.picseesee.net;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import picsee.com.hongenit.picseesee.util.LogUtil;

/**
 * Created by hongenit on 18/1/29.
 */

public class NetClientJava implements INetClient {
    private static final String TAGH = "tagh";
    private String TAGF = "tagf";

    @Override
    public void sendRequest(@Nullable String url) {
        Request request = new Request.Builder().url(url).build();
        new OkHttpClient().newCall(request).enqueue(new MyCallBack());

    }


    private class MyCallBack implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            LogUtil.INSTANCE.e(TAGF, e.toString());
            LogUtil.INSTANCE.e(TAGH, e.toString());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

        }
    }
}
