package mysmax.com.retrofitapplication.webservice.soap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Map;
import java.util.Vector;


class SoapSingleton {
    private static final SoapSingleton ourInstance = new SoapSingleton();
    private static final String BASE_URL = "http://www.mywebservice.com/service/apiservice.aspx";
    private static final String NAME_SPACE = "MyWebServiceName";
    private static final String NAME = "MyMethodName";
    private static final String ACTION = "MyWebServiceName/MyMethodName";

    static SoapSingleton getInstance() {
        return ourInstance;
    }

    private SoapSingleton() {
    }

    synchronized public SoapObject stubSoapObject(Map<String,String> attributes, Vector<String> dataTypeHint)
    {
        SoapObject soapObject = new SoapObject(NAME_SPACE,NAME);
        int index = 0;
        for(Map.Entry item:attributes.entrySet())
        {
            PropertyInfo info = new PropertyInfo();
            info.setName(item.getKey().toString());
            info.setValue(item.getValue());
            switch (dataTypeHint.get(index).toLowerCase())
            {
                case "string": info.setType(String.class); break;
                case "int" : info.setType(Integer.class); break;
                case "double" : info.setType(Double.class); break;
                case "boolean" : info.setType(Boolean.class); break;
            }
            soapObject.addProperty(info);
            index++;
        }

        return soapObject;
    }

    synchronized public SoapSerializationEnvelope stubSoapSerializationEnvelope(SoapObject soapObject)
    {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soapObject);
        return envelope;
    }

    synchronized public String stubHttp(SoapSerializationEnvelope envelope)
    {
        String response = null;
        try {
            HttpTransportSE transportSE = new HttpTransportSE(BASE_URL);
            transportSE.call(ACTION,envelope);
            SoapPrimitive responseData = (SoapPrimitive) envelope.getResponse();
            response = responseData.toString();

        } catch (Exception e)
        {
           e.printStackTrace();
        }
        return response;
    }

}
