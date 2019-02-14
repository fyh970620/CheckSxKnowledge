package com.bsnnms.exception;

public class DataAccessException extends Exception
{
  Throwable exceptionCause=null;
  public DataAccessException(String pExceptionMsg)
  {
    super(pExceptionMsg);
  }
  public DataAccessException(String pExceptionMsg,Throwable pException)
  {
   super(pExceptionMsg);
   this.exceptionCause =pException;
 }
public void printStackTrace()
{
  if(exceptionCause!=null)
  {
  System.err.println("数据访问异常：") ;
  exceptionCause.printStackTrace() ;
  }
}

}
