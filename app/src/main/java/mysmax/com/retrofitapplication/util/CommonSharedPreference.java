package mysmax.com.retrofitapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

public class CommonSharedPreference {
    private Context context;
    private SharedPreferences prfManager;

    public CommonSharedPreference(Context contxt)
    {
        // Global Interface to access resource, activities of components
        this.context = contxt;
        // Stores the Key,Value pair data to retrieve later
        this.prfManager = context.getSharedPreferences("RetrofitApplicaton",Context.MODE_PRIVATE);

    }

    public void setName(String name)
    {
        if (prfManager != null) prfManager.edit().putString("UserName",name).apply();
    }

    public String getName()
    {
        return prfManager.getString("UserName",null);
    }
}
