/**
 * Copyright (c) 2015 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jmnarloch.spring.cloud.zuul.support;

import io.jmnarloch.spring.cloud.zuul.matcher.RouteMatcher;
import io.jmnarloch.spring.cloud.zuul.route.MatcherProxyRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.ZuulProxyConfiguration;
import org.springframework.cloud.netflix.zuul.filters.ProxyRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Registers the {@link MatcherProxyRouteLocator} instance that delegates to the configured {@link RouteMatcher} for
 * finding the best matching routes.
 *
 * @author Jakub Narloch
 */
@Configuration
public class ZuulProxyMatcherConfiguration extends ZuulProxyConfiguration {

    @Autowired
    private DiscoveryClient discovery;

    @Autowired
    private ZuulProperties zuulProperties;

    @Autowired
    private ServerProperties server;

    @Autowired
    private RouteMatcher routeMatcher;

    @Override
    public ProxyRouteLocator routeLocator() {
        return new MatcherProxyRouteLocator(server.getServletPath(), discovery, zuulProperties, routeMatcher);
    }
}
