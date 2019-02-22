package com.beyond.util.webservice.service;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(serviceName = "HYBService")
public class Hello {
    @WebMethod(operationName = "HYBMethod")
    @WebResult(name = "HYBResponse")
    public String sayHello(@WebParam(header = true, name = "HYBHeader") String heard, @WebParam(name = "HYBRequest") String name) {
        return heard + ".hello " + name;
    }

    //排除当前方法
    @WebMethod(exclude = true)
    public String sayHi(String name) {
        return "hi " + name;
    }

    public static void main(String[] args) {
        //服务的地址
        String address = "http://localhost:8989/HYBService";
        Hello implementor = new Hello();
        //发布服务，第一个参数是服务的地址，第二个参数是发布出去服务的方法
        //发布成功之后可以通过  http://localhost:8989/HYBService?wsdl  访问
        Endpoint.publish(address, implementor);
    }
}
