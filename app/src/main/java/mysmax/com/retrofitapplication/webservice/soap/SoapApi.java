package mysmax.com.retrofitapplication.webservice.soap;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class SoapApi {

public String postYourRequest()
    {
        SoapSingleton request = SoapSingleton.getInstance();

        Map<String,String> mapValues = new HashMap();
        mapValues.put("name","value");
        Vector<String> mapDataTypeHint = new Vector<>();
        mapDataTypeHint.add("string");

        SoapObject object = request.stubSoapObject(mapValues,mapDataTypeHint);
        SoapSerializationEnvelope envelope = request.stubSoapSerializationEnvelope(object);
        String response = request.stubHttp(envelope);

        return response;
    }
}
