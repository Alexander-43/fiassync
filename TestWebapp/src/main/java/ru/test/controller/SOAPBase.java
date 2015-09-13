package ru.test.controller;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class SOAPBase {
	
	public static final Logger logger = Logger.getLogger(SOAPBase.class);
	
	public static void main(String[] args) throws Exception {
		
	    String body ="<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><GetAllDownloadFileInfo xmlns=\"http://fias.nalog.ru/WebServices/Public/DownloadService.asmx\" /></soap:Body></soap:Envelope>";
	   
        HttpPost httppost = new HttpPost("http://fias.nalog.ru/WebServices/Public/DownloadService.asmx");

        // Request parameters and other properties.
        StringEntity stringentity=new StringEntity(body,"UTF-8");
        stringentity.setContentType("text/xml");
        stringentity.setChunked(true);
        httppost.setEntity(stringentity);
        httppost.addHeader("Accept" , "text/xml");
        httppost.addHeader("Host", "fias.nalog.ru");
        httppost.addHeader("SOAPAction","http://fias.nalog.ru/WebServices/Public/DownloadService.asmx/GetAllDownloadFileInfo");
        //Execute and get the response.
                HttpClient httpclient = HttpClientBuilder.create().build();
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        String strresponse = null;
        if(entity!=null) {
            strresponse = EntityUtils.toString(entity);
            logger.error(strresponse);
        }
	}
}
