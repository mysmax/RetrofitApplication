package mysmax.com.retrofitapplication.webservice.volley;


import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mysmax.com.retrofitapplication.webservice.volley.custom.CustomResponseList;
import mysmax.com.retrofitapplication.webservice.volley.custom.GsonRequest;


public class VolleyApi  extends AppCompatActivity{
    // http://api.openweathermap.org/data
    private static final String BASE_URL = "https://www.yourdomain.com";

    public void postYourStringRequest()
    {
        // Get a RequestQueue
        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        // Request a string response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // response.substring(0,500)
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.i("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }

    public void postYourJsonObjectRequest()
    {
        // Get a RequestQueue
        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        // POST parameters
        Map<String, String> params = new HashMap<String, String>();
        params.put("key", "value");

        JSONObject jsonObj = new JSONObject(params);
        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, BASE_URL, jsonObj, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

        // Add the request to the RequestQueue.
        queue.add(jsonObjRequest);

    }

    public void postYourJsonArrayRequest(final JSONObject jsonRequest)
    {
        // Get a RequestQueue
        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        // Request a jsonArray response from the provided URL
        JsonArrayRequest requestWithJsnArr = new JsonArrayRequest(Request.Method.POST, BASE_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                try {
                    params.put("key1", jsonRequest.getString("key1"));
                    params.put("key2", Integer.toString(jsonRequest.getInt("key2")));
                    params.put("key3", Boolean.toString(jsonRequest.getBoolean("key3")));
                    params.put("key4", jsonRequest.getJSONArray("key4").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(requestWithJsnArr);
    }

    public void postYourImageRequest()
    {
        // Get a RequestQueue
        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        ImageRequest imageRequest = new ImageRequest(" ,887966933&fm=19&gp=0.jpg",
                new Response.Listener<Bitmap>()
                {

                    @Override
                    public void onResponse(Bitmap response)
                    {
                        //mImageView.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, null);

        // Add the request to the RequestQueue.
        queue.add(imageRequest);
    }

    public void postYourCustomRequest()
    {
        //class is the POJO class for serialising the response using Gson. (extends serializable)
        // Get a RequestQueue
        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        // Custom Request
        GsonRequest gsonRequest = new GsonRequest(BASE_URL, CustomResponseList.class, null, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                // Handle response
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });
        // Add the request to the RequestQueue.
        queue.add(gsonRequest);
    }

    public void parseResponse()
    {
        /*
        {
            @Override
            protected Response parseNetworkResponse(NetworkResponse response) {
                try {

                    String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new JSONArray(jsonString), HttpHeaderParser.parseCacheHeaders(response));

                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
        }

        @Override
        public void onResponse(JsonElement element) {
        if(element.isJsonArray()){
            JsonArray array=element.getAsJsonArray();
            //read response here
        }else if(element.isJsonObject()){
            JsonObject object=element.getAsJsonObject();
            //read response here
        }
        }

        @Override
        public void onResponse(String s) {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(s);

            if (element.isJsonArray()) {
                JsonArray array = element.getAsJsonArray();
                //read response here
            } else if (element.isJsonObject()) {
                JsonObject object = element.getAsJsonObject();
                //read response here
            }
        }
        */

    }

    public void multipartPostRequest()
    {

    }
    /*
    private void uploadImage(Bitmap thumbnailBitmap) {
        try {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (thumbnailBitmap != null) {
                thumbnailBitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            }

            byte[] fileByteArray = byteArrayOutputStream.toByteArray();

            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream2);
            try {
                // the first file
                buildPart(dataOutputStream, fileByteArray, "picture.jpg");
                // send multipart form data necesssary after file data
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                // pass to multipart body
                multipartBody = byteArrayOutputStream2.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }


            String url = ApplicationConstant.HOME_URL + UPLOAD_PROFILE_PIC_CONTROLLER ;
            MultipartRequest multipartRequest = new MultipartRequest(url, null, mimeType, multipartBody, new Response.Listener<NetworkResponse>() {
                @Override
                public void onResponse(NetworkResponse response) {
                    //Toast.makeText(mContext, getString(R.string.refreshing_profile_image), Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        //Toast.makeText(getApplicationContext(), getString(R.string.error_please_try_again), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            VolleySingleton.getInstance(this).addToRequestQueue(multipartRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildPart(DataOutputStream dataOutputStream, byte[] fileData, String fileName) throws IOException {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + fileName + "\"" + lineEnd);
        dataOutputStream.writeBytes(lineEnd);

        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(fileData);
        int bytesAvailable = fileInputStream.available();

        int maxBufferSize = 1024 * 1024;
        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
        byte[] buffer = new byte[bufferSize];

        // read file and write it into form...
        int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

        while (bytesRead > 0) {
            dataOutputStream.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }

        dataOutputStream.writeBytes(lineEnd);
    }
    */
}
