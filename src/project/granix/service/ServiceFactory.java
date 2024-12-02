/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import service.custom.impl.buyerServiceImpl;
import service.custom.impl.stockServiceImpl;
import service.custom.impl.userServiceImpl;



/**
 *
 * @author ASUS
 */
public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    private ServiceFactory(){
        
    }
    public static ServiceFactory getInstance(){
        if(serviceFactory == null){
            serviceFactory = new ServiceFactory();
            
        }
        return serviceFactory;
    }
    public SuperService getService(ServiceType type){
        switch(type){
            //case STOCK:
            ///    return new stockServiceImpl();
            case BUYER:
                return new buyerServiceImpl();
            case EMPLOYEE:
                return new userServiceImpl();
            case STOCK:
                return new stockServiceImpl();
            default:
                return null;
        }
    }

    
    public enum ServiceType{
        EMPLOYEE,BUYER,STOCK//,ITEM,MANAGE
    }
}
