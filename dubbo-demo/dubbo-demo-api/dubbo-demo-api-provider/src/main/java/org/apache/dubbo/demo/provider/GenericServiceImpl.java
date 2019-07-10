package org.apache.dubbo.demo.provider;

import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:20 AM 7/10/19.
 */
public class GenericServiceImpl implements GenericService {
    @Override
    public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
        if ("sayHello".equals(method)){
            if (args != null && args.length != 0){
                return "Hello " + args[0] + ", response from provider: " + RpcContext.getContext().getLocalAddress();
            }
        }
        return null;
    }
}
