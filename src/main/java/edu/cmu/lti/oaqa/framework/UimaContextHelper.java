package edu.cmu.lti.oaqa.framework;

import org.apache.uima.UimaContext;

public class UimaContextHelper {

  public static boolean getConfigParameterBooleanValue(UimaContext aContext, String paramName,
          boolean defaultValue) {
    try {
      return (Boolean) aContext.getConfigParameterValue(paramName);
    } catch (ClassCastException e) {
      return Boolean.parseBoolean((String) aContext.getConfigParameterValue(paramName));
    } catch (NullPointerException e) {
      System.out.println(" * Parameter \"" + paramName + "\" cannot be found, use default value \""
              + defaultValue + "\" instead.");
      return defaultValue;
    }
  }

  public static float getConfigParameterFloatValue(UimaContext aContext, String paramName,
          float defaultValue) {
    try {
      return (Float) aContext.getConfigParameterValue(paramName);
    } catch (ClassCastException e) {
      try {
      return (Integer) aContext.getConfigParameterValue(paramName);
      } catch (ClassCastException err) {
        return Float.parseFloat((String) aContext.getConfigParameterValue(paramName));
      }
    }catch (NullPointerException e) {
      System.out.println(" * Parameter \"" + paramName + "\" cannot be found, use default value \""
              + defaultValue + "\" instead.");
      return defaultValue;
    }
  }

  public static int getConfigParameterIntValue(UimaContext aContext, String paramName,
          int defaultValue) {
    try {
      return (Integer) aContext.getConfigParameterValue(paramName);
    } catch (ClassCastException e) {
      return Integer.parseInt((String) aContext.getConfigParameterValue(paramName));
    } catch (NullPointerException e) {
      System.out.println(" * Parameter \"" + paramName + "\" cannot be found, use default value \""
              + defaultValue + "\" instead.");
      return defaultValue;
    }
  }

  public static String getConfigParameterStringValue(UimaContext aContext, String paramName,
          String defaultValue) {
    String value = (String) aContext.getConfigParameterValue(paramName);
    if (value != null) {
      return value;
    } else {
      System.out.println(" * Parameter \"" + paramName + "\" cannot be found, use default value \""
              + defaultValue + "\" instead.");
      return defaultValue;
    }
  }

  public static Object getConfigParameterClassInstance(UimaContext aContext, String paramName,
          String defaultClassName) {
    String className = (String) getConfigParameterStringValue(aContext, paramName, defaultClassName);
    try {
      return Class.forName(className).newInstance();
    } catch (Exception e) {
      System.out.println(" * Class \"" + className + "\" cannot be set");
      return new Object();
    }
  }
}
