package ir.home.webservice;

import java.io.IOException;
import java.util.HashMap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class WebService {
	protected final String NAMESPACE = "http://fpkaroon.com/";

	protected String getURL() {
		return NAMESPACE + "Service.asmx";
	}

	public SoapObject callMethod(String methodName,
			HashMap<String, Object> params) throws IOException,
			XmlPullParserException {

		SoapObject request = new SoapObject(NAMESPACE, methodName);

		if (params != null && params.size() > 0)
			for (String key : params.keySet()) {
				request.addProperty(key, params.get(key));
			}

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;

		envelope.setOutputSoapObject(request);

		HttpTransportSE androidHttpTransport = new HttpTransportSE(getURL());

		androidHttpTransport.call(NAMESPACE + methodName, envelope);

		SoapObject response = (SoapObject) envelope.getResponse();

		return response;

	}
}