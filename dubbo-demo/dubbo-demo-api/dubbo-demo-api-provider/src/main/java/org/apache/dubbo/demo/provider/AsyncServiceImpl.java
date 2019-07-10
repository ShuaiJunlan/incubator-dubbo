package org.apache.dubbo.demo.provider;

import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:44 PM 6/28/19.
 */
public class AsyncServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        System.out.println("Main sayHello() method start.");
        final AsyncContext asyncContext = RpcContext.startAsync();
        new Thread(() -> {
            asyncContext.signalContextSwitch();
            System.out.println("Attachment from consumer: " + RpcContext.getContext().getAttachment("consumer-key1"));
            System.out.println("    -- Async start.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            asyncContext.write("Hello " + name + ", response from async provider.");
            System.out.println("    -- Async end.");
        }).start();
        System.out.println("Main sayHello() method end.");
        return "Hello " + name + ", response from async provider: " + RpcContext.getContext().getLocalAddress();
    }
}
