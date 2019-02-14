package com.ffcs.itm.web.knowledges.service;

import com.bsnnms.exception.ApplicationException;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.client.AsyncCallback;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.TimeZone;
import java.util.Vector;

public class KnowledgeRpcClient implements AsyncCallback {
    private static KnowledgeRpcClient instance;
    private static final Vector EMPTY_PARAM = new Vector();
    private XmlRpcClientConfigImpl xmlRpcConfig = new XmlRpcClientConfigImpl();
    private XmlRpcClient xmlRpcClient = null;
    private Object object = null;

    public synchronized static KnowledgeRpcClient getInstance(String server_url) throws ApplicationException {
        if (instance == null)
            instance = new KnowledgeRpcClient(server_url);
        return instance;
    }

    public KnowledgeRpcClient(String server_url) throws ApplicationException {
        try {
            xmlRpcConfig.setTimeZone(TimeZone.getTimeZone("UTC"));
            xmlRpcConfig.setEncoding("GBK");
            xmlRpcConfig.setServerURL(new URL(server_url));
            this.open(server_url);
            xmlRpcClient = new XmlRpcClient();
            xmlRpcClient.setConfig(xmlRpcConfig);
            xmlRpcClient.setMaxThreads(15);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    public boolean execute(final String rpcMethodName) {
        return execute(rpcMethodName, EMPTY_PARAM);
    }

    public boolean execute(final String rpcMethodName, Vector parameters) {
        if (xmlRpcClient == null)
            return false;
        boolean ret = true;
        try {
            Object result = xmlRpcClient.execute(rpcMethodName, parameters);
            object = result;
        } catch (XmlRpcException e) {
            e.printStackTrace();
            Hashtable fault = new Hashtable();
            fault.put("faultCode", String.valueOf(e.code));
            fault.put("faultString", e.getMessage());
            object = (Object) fault;
            ret = false;
        }
        return ret;
    }

    public void executeAsync(final String rpcMethodName) throws ApplicationException {
        executeAsync(rpcMethodName, EMPTY_PARAM);
    }

    public void executeAsync(final String rpcMethodName, Vector parameters) throws ApplicationException {
        if (xmlRpcClient == null)
            throw new ApplicationException("XMLRPC未初始化!");
        try {
            //xmlRpcClient.executeAsync(rpcMethodName, parameters, this);
            xmlRpcClient.executeAsync(rpcMethodName, parameters, this);
        } catch (Exception e) {
            e.printStackTrace();
            Hashtable fault = new Hashtable();
            fault.put("faultCode", "-1");
            fault.put("faultString", e.getMessage());
            object = (Object) fault;
        }
    }

    public Object getResult() {
        return object;
    }

    public void handleResult(XmlRpcRequest paramXmlRpcRequest, Object result) {
        //System.err.println ("received: "+result);
        object = result;
    }

    public void handleError(XmlRpcRequest paramXmlRpcRequest, Throwable paramThrowable) {
        //System.err.println ("Error: "+exception);
        Hashtable fault = new Hashtable();
        fault.put("faultCode", "-1");
        fault.put("faultString", paramThrowable.getMessage());
        object = (Object) fault;
    }

    /**
     * 网络连接测试
     * @param serverURL
     * @throws ApplicationException
     */
    private void open(String serverURL) throws ApplicationException {
        URL urlAddr = null;
        URLConnection urlc = null;
        HttpURLConnection httpConnection = null;
        PrintStream ps = null;
        try {
            urlAddr = new URL(serverURL);
            urlc = urlAddr.openConnection();
            urlc.setDoOutput(true);
            urlc.setDoInput(true);
            httpConnection = (HttpURLConnection) urlc;
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "text/xml;charset=GBK");
            httpConnection.setRequestProperty("Content-Length", "10");
            ps = new PrintStream(httpConnection.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("远程XMLRPC服务连接失败! 原因:" + e.getMessage());
        } finally {
            if (ps != null)
                ps.close();
        }
    }
}
