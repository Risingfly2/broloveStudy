package io.gen.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class RpcClient {
    public <T> T refer(final Class<T> interfaceClass, final String host, final int port) throws Exception{
        if (interfaceClass == null){
            throw new IllegalArgumentException("Interface class == null");
        }
        if (!interfaceClass.isInterface()){
            throw new IllegalArgumentException("The" + interfaceClass.getName() + "must be interface class");
        }
        if (host == null || host.length() == 0){
            throw new IllegalArgumentException("Host == null");
        }
        if (port <= 0 || port > 65535){
            throw new IllegalArgumentException("Invalid port "+port);
        }
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket(host, port);
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeUTF(method.getName());
                output.writeObject(method.getParameterTypes());
                output.writeObject(args);
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                Object result = input.readObject();
                if (result instanceof Throwable){
                    throw (Throwable) result;
                }
                return result;
            }
        });
    }
}
