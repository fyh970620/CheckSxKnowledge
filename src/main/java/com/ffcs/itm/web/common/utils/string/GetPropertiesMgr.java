package com.ffcs.itm.web.common.utils.string;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Administrator
 *
 */
public class GetPropertiesMgr {
  /**
   *
   */
  public GetPropertiesMgr() {
  }

  /**
   * 根据属性名称取得属性文件的属性值
   *
   * @param lpPropertiesFileName
   * @param lpPropertiesName
   * @return
   * @author: panchh
   * @修改记录：
   *
   * ==============================================================<br>
   * 日期:Dec 28, 2009      panchh         创建方法，并实现其功能
   *
   * ==============================================================<br>
   */
  public String getPropertiesInfos(String lpPropertiesFileName, String lpPropertiesName){
    String lpPropertiesValue = "";

    InputStream is = getClass().getResourceAsStream("/" + lpPropertiesFileName);
    Properties props = new Properties();
    try{
      props.load(is);
      is.close();
      lpPropertiesValue = StrUtil.GBToUnicode(props.getProperty(lpPropertiesName));
    }catch(Exception e){
      System.err.println("不能读取属性文件。请确保属性文件"+lpPropertiesFileName+"在你的CLASSPATH中");
    }
    return lpPropertiesValue;
  }

  /**
   * 取得属性文件各属性名称的属性值
   *
   * @param lpPropertiesFileName
   * @param lpPropertiesNameArr
   * @return
   * @author: panchh
   * @修改记录：
   *
   * ==============================================================<br>
   * 日期:Dec 28, 2009      panchh         创建方法，并实现其功能
   *
   * ==============================================================<br>
   */
  public String[] getPropertiesInfos(String lpPropertiesFileName, String[] lpPropertiesNameArr){
    String[] lpPropertiesValueArr = null;

    InputStream is = getClass().getResourceAsStream("/" + lpPropertiesFileName);
    Properties props = new Properties();
    try{
      props.load(is);
      is.close();
      if (lpPropertiesNameArr != null){
        lpPropertiesValueArr = new String[lpPropertiesNameArr.length];
        for (int i = 0; i < lpPropertiesNameArr.length; i++) {
          String lptmpValue = StrUtil.GBToUnicode(props.getProperty(lpPropertiesNameArr[i]));
          lpPropertiesValueArr[i] = lptmpValue;
        }
      }
    }catch(Exception e){
      System.err.println("不能读取属性文件。请确保属性文件"+lpPropertiesFileName+"在你的CLASSPATH中");
    }
    return lpPropertiesValueArr;
  }

  /**
   * 根据属性文件名和属性项目名取得相应的属性文件信息
   *
   * @param lpFileName
   * @param lpPropID
   * @return
   * @author: panchh
   * @修改记录：
   *
   * ==============================================================<br>
   * 日期:Dec 28, 2009      panchh         创建方法，并实现其功能
   *
   * ==============================================================<br>
   */
  public String getMsgByProperties(String lpFileName, String lpPropID){
    String lpReturnMsg = "";
    Properties props = new Properties();
    try {
      InputStream propsInputStream = getClass().getResourceAsStream("/"+ lpFileName);
      props.load(propsInputStream);
      lpReturnMsg = props.getProperty(lpPropID);
      lpReturnMsg = StrUtil.GBToUnicode(lpReturnMsg);
    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("不能读取属性文件。请确保属性文件"+ lpFileName +"在你的CLASSPATH中:" + ex.getMessage());
      lpReturnMsg = "不能读取属性文件。";
    }
    if (lpReturnMsg == null || lpReturnMsg.equals("")){
      System.out.println("没有此错误代号信息，请核查"+ lpFileName +"文件。");
      lpReturnMsg = "没有此错误代号信息。";
    }
    return lpReturnMsg;
  }


}