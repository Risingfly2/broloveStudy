package io.gen.rpc.client;

import io.gen.rpc.RpcClient;

public class ClientDemo {
    public static void main(String[] args) throws Exception{
        RpcClient client = new RpcClient();
        CalculatorService service = client.refer(CalculatorService.class, "127.0.0.1", 1234);
        for (int i = 0; i < 1000; i++) {
            int result = service.add(i,++i);
            System.out.println("result= "+result);
        }

    }
}
