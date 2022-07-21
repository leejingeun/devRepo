<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="false"
    
%><%!

public final String AUTH_URL = "/svc/tk/Auth.do";
public final String AUTH_RESOLVE_URL = "/svc/tk/AuthResolve.do";
public final String AUTH_ASSERT_URL = "/svc/tk/AuthAssert.do";
public final String AUTH_FEDERATE_URL = "/svc/tk/AuthFederate.do";
public final String LOGIN_URL = "/svc/tk/Login.do";
public final String LOGOUT_URL = "/svc/tk/SLO.do";

public final String RELAY_STATE_NAME = "RelayState";
public final String ID_NAME = "id";
public final String SECRET_NAME = "secret";
public final String NAMEID_NAME = "nameId";
public final String STATUS_NAME = "status";
public final String TARGET_ID_NAME = "targetId";
public final String DATA_NAME = "data";
public final String AC_NAME = "ac";
public final String IFA_NAME = "ifa";
public final String TOKEN_NAME = "t";
public final String SP_ID_NAME = "spid";

public final String SUCCESS = "success";
public final String FAILURE_CAUSE = "failureCause";

public final String SSO_SESSION_NAME = "eXSignOn.session.userid";
public final String SSO_SESSION_ANONYMOUSE = "anonymous";
public final String SSO_SESSION_ANONYMOUSE_IDENTIFY = "anonymous_identify";

%><%!

private String encode(String data) {
    if(data == null || "".equals(data.trim())) {
        return "";
    }
    
    try {
        return java.net.URLEncoder.encode(data, "utf-8");
    } catch(java.io.UnsupportedEncodingException e) {
        return java.net.URLEncoder.encode(data);
    }
}

public String generateUrl(String idpUrl, String subUrl) {
    if((idpUrl == null || "".equals(idpUrl.trim())) && (subUrl == null || "".equals(subUrl.trim()))) {
        return null;
    }
    
    StringBuffer url = new StringBuffer(idpUrl);
    if(!idpUrl.endsWith("/")) {
        url.append("/");        
    }
    
    url.append(subUrl);
    
    String urlStr = url.toString();
    
    urlStr = urlStr.replaceAll("[/]+", "/");
    
    if(urlStr.startsWith("http:/")) {
        urlStr = urlStr.replaceFirst("http:/", "http://");
    } else if(urlStr.startsWith("https:/")) {
        urlStr = urlStr.replaceFirst("https:/", "https://");
    }
    
    return urlStr;
}

public String generateParam(java.util.Map param) {
    StringBuffer paramBuf = new StringBuffer();
    
    java.util.Iterator paramIter = param.entrySet().iterator();
    
    while(paramIter.hasNext()) {
        java.util.Map.Entry entry = (java.util.Map.Entry) paramIter.next();
        String name = (String) entry.getKey();
        String value = (String) entry.getValue();
        
        paramBuf.append(this.encode(name));
        paramBuf.append("=");
        paramBuf.append(this.encode(value));
        paramBuf.append("&");
    }
    
    return paramBuf.toString();
}

public String generateUrlWithParam(String idpUrl, String subUrl, java.util.Map param) {
    String url = this.generateUrl(idpUrl, subUrl);
    
    if(url == null) {
        return null;
    }
    
    String paramStr = this.generateParam(param);
    
    return url + "?" + paramStr;
}

private java.net.HttpURLConnection getConnection(java.net.URL url) throws java.lang.Exception {
    String protocol = url.getProtocol().toLowerCase();
    
    java.net.HttpURLConnection conn = null;
    
    if("http".equals(protocol)) {
        conn = (java.net.HttpURLConnection) url.openConnection();
    } else if("https".equals(protocol)) {
        conn = (javax.net.ssl.HttpsURLConnection) url.openConnection();
    }
    
    if(conn != null) {
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    }
    
    return conn;
}

public String httpRequest(String url, java.util.Map paramMap) {
    java.net.URL requestUrl = null;
    java.net.HttpURLConnection conn = null;
    String result = null;
    java.io.ByteArrayOutputStream out = null;
    java.io.OutputStream writer = null;
    java.io.InputStream reader = null;
    
    try {
        requestUrl = new java.net.URL(url);
        
        conn = this.getConnection(requestUrl);

        writer = conn.getOutputStream();
        
        String param = this.generateParam(paramMap);
        
        writer.write(param.getBytes("utf-8"));
        writer.flush();
        
        writer.close();    

        try {
            reader = conn.getInputStream();
        } catch(java.io.IOException ioe) {
            reader = conn.getErrorStream();
        }
        
        out = new java.io.ByteArrayOutputStream();
        
        int read = -1;
        byte[] b = new byte[256];
        while((read = reader.read(b)) != -1) {
            out.write(b, 0, read);
        }
        out.flush();
        
        result = new String(out.toByteArray(), "utf-8");
        
        conn.disconnect();
        
        out.close();
        reader.close();
        
    } catch(java.lang.Exception e) {
        e.printStackTrace();
    } finally {
        if(conn != null) {
            conn.disconnect();
        }
        
        if(writer != null) {
            try {
                writer.close();
            } catch(java.lang.Exception e){}
        }
        
        if(out != null) {
            try {
                out.close();
            } catch(java.lang.Exception e){}
        }
        
        if(reader != null) {
            try {
                reader.close();
            } catch(java.lang.Exception e){}
        }
    }
    
    return result;
}

public String addContextPath(HttpServletRequest request, String path) {
    String ctxPath = request.getContextPath();
    
    if(ctxPath == null || "".equals(ctxPath.trim())) {
        ctxPath = "/";
    }
    
    String relativePath = ctxPath + "/" + path;
    
    relativePath = relativePath.replaceAll("[/]+", "/");
    
    return relativePath;
}

%>