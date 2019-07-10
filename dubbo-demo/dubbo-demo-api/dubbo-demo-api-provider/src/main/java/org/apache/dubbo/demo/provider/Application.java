/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.dubbo.demo.provider;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.rpc.service.GenericService;

import java.io.IOException;

public class Application {
    /**
     * In order to make sure multicast registry works, need to specify '-Djava.net.preferIPv4Stack=true' before
     * launch the application
     */
    public static void main(String[] args) throws Exception {
        export();
        // asyncExport();
        // genericExport();
    }

    private static void export() throws IOException {
        ServiceConfig<DemoServiceImpl> service = new ServiceConfig<>();
        service.setRef(new DemoServiceImpl());

        // ServiceConfig<AsyncServiceImpl> service = new ServiceConfig<>();
        // service.setRef(new AsyncServiceImpl());

        service.setApplication(new ApplicationConfig("dubbo-demo-api-provider"));
        service.setRegistry(new RegistryConfig("multicast://224.5.6.7:1234"));
        service.setInterface(DemoService.class);
        // service.setRetries(10); //retries
        service.export();
        System.in.read();
    }

    private static void asyncExport() throws IOException {
        ServiceConfig<AsyncServiceImpl> service = new ServiceConfig<>();
        service.setRef(new AsyncServiceImpl());

        service.setApplication(new ApplicationConfig("dubbo-demo-api-provider"));
        service.setRegistry(new RegistryConfig("multicast://224.5.6.7:1234"));
        service.setInterface(DemoService.class);
        // service.setRetries(10); //retries
        service.export();
        System.in.read();
    }

    private static void genericExport() throws IOException {
        ServiceConfig<GenericService> service = new ServiceConfig<>();
        service.setRef(new GenericServiceImpl());

        service.setApplication(new ApplicationConfig("dubbo-demo-api-provider"));
        service.setRegistry(new RegistryConfig("multicast://224.5.6.7:1234"));
        service.setInterface(DemoService.class);
        service.setGeneric("true");
        // service.setRetries(10); //retries
        service.export();
        System.in.read();
    }
}
