package io.gen.rpc.server;

import io.gen.rpc.RpcServer;

public class RpcServerApplication {
    public static void main(String[] args)throws Exception {
        CalculatorService service = new CalculatorServiceImpl();
        RpcServer server = new RpcServer();
        server.export(service, 1234);
    }
}
