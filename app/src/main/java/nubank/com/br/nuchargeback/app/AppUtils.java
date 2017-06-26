package nubank.com.br.nuchargeback.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AppUtils {

    private static final String TYPE_WIFI = "WIFI";
    private static final String TYPE_MOBILE = "MOBILE";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        if (connManager == null) {
            return false;
        }

        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String connType = networkInfo.getTypeName();
            if (connType.equalsIgnoreCase(TYPE_WIFI) || connType.equalsIgnoreCase(TYPE_MOBILE)) {
                return true;
            }
        }

        return false;
    }

}
