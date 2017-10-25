package Http;

/**
 * Created by charlie on 2017/10/25.
 */

import android.content.Context;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by charlie on 2017/6/12.
 */

public class Network_core{

    private static Context context;
    private OkHttpUtils mokHttpClient;
    private RequestCall mcall;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String SERVER_HOST = "http://140.123.92.75:8080/ServletSample";

    //!!注意 使用前需要實例化 Network_core()
    public Network_core(Context context)
    {
        this.context = context;
    }

    //取消目前的Call請求
    public void Cancel_network(){
        if(mcall!=null)
            mcall.cancel();
    }

    /**
     * 查詢K&Y Server資料
     * @param dataType 資料所屬類別
     * @return    void
     */

    public void requestKY(String dataType) {
        Log.e("debug", "in requestKY");
        String url = SERVER_HOST + "/JSoup?dataType=" + dataType;
        Log.e("test url", url);
        mcall = mokHttpClient
                .get()
                .url(url)
                .build();

        mcall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("error", e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("requestKY:", response);
                mCallback.response(response);
            }
        });
    }


    /*
    * CallBack
    * */
    private netCallback mCallback;

    public interface netCallback{
        public abstract String response(String response);
    }

    public void setCallback(netCallback callback){
        this.mCallback = callback;
    }
}