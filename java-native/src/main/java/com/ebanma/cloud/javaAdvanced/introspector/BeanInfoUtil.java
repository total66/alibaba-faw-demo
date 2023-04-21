package com.ebanma.cloud.javaAdvanced.introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @author 于秦涛
 * @version $ Id: PropertyDescriptor, v 0.1 2023/03/14 21:53 98077 Exp $
 */
public class BeanInfoUtil {

    public static void setProperty(UserInfo userInfo,String userName)throws Exception{
        PropertyDescriptor propDesc=new PropertyDescriptor(userName,UserInfo.class);
        Method methodSetUserName=propDesc.getWriteMethod();
        methodSetUserName.invoke(userInfo, "wong");
        System.out.println("set userName:"+userInfo.getUserName());
    }

    public static void getProperty(UserInfo userInfo,String userName)throws Exception{
        PropertyDescriptor proDescriptor =new PropertyDescriptor(userName,UserInfo.class);
        Method methodGetUserName=proDescriptor.getReadMethod();
        Object objUserName=methodGetUserName.invoke(userInfo);
        System.out.println("get "+userName+" :"+objUserName.toString());
    }

    public static void setPropertyByIntrospector(UserInfo userInfo,String userName)throws Exception{
        BeanInfo beanInfo= Introspector.getBeanInfo(UserInfo.class);
        PropertyDescriptor[] proDescrtptors=beanInfo.getPropertyDescriptors();
        if(proDescrtptors!=null&&proDescrtptors.length>0){
            for(PropertyDescriptor propDesc:proDescrtptors){
                if(propDesc.getName().equals(userName)){
                    Method methodSetUserName=propDesc.getWriteMethod();
                    methodSetUserName.invoke(userInfo, "alan");
                    System.out.println("set userName:"+userInfo.getUserName());
                    break;
                }
            }
        }
    }

    public static void getPropertyByIntrospector(UserInfo userInfo,String userName)throws Exception{
        BeanInfo beanInfo=Introspector.getBeanInfo(UserInfo.class);
        PropertyDescriptor[] proDescrtptors=beanInfo.getPropertyDescriptors();
        if(proDescrtptors!=null&&proDescrtptors.length>0){
            for(PropertyDescriptor propDesc:proDescrtptors){
                if(propDesc.getName().equals(userName)){
                    Method methodGetUserName=propDesc.getReadMethod();
                    Object objUserName=methodGetUserName.invoke(userInfo);
                    System.out.println("get userName:"+objUserName.toString());
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        UserInfo user = new UserInfo();
        user.setUserId(666l);
        setProperty(user,"userName");
        getProperty(user,"userId");
        setPropertyByIntrospector(user,"userName");
        getPropertyByIntrospector(user,"userId");
    }

}

