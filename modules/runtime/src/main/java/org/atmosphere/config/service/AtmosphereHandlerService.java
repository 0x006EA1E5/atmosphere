/*
 * Copyright 2018 Async-IO.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.atmosphere.config.service;

import org.atmosphere.cache.DefaultBroadcasterCache;
import org.atmosphere.runtime.AtmosphereInterceptor;
import org.atmosphere.runtime.AtmosphereResourceEventListener;
import org.atmosphere.runtime.BroadcastFilter;
import org.atmosphere.runtime.Broadcaster;
import org.atmosphere.runtime.BroadcasterCache;
import org.atmosphere.runtime.DefaultBroadcaster;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation for {@link org.atmosphere.runtime.AtmosphereHandler}
 *
 * @author Jeanfrancois Arcand
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AtmosphereHandlerService {

    /**
     * The {@link org.atmosphere.runtime.Broadcaster} class name
     *
     * @return The {@link org.atmosphere.runtime.Broadcaster} class name
     */
    Class<? extends Broadcaster> broadcaster() default DefaultBroadcaster.class;

    /**
     * A list of {@link BroadcastFilter}
     */
    Class<? extends BroadcastFilter>[] broadcastFilters() default {};

    /**
     * The mapping path or context-root used to map this AtmosphereHandler
     *
     * @return mapping path or context-root used to map this AtmosphereHandler
     */
    String path() default "/";

    /**
     * Properties that will be passed to the associated {@link org.atmosphere.runtime.AtmosphereHandler}. Properties are defined
     * delimited using "=" and separated using comma.
     *
     * @return an array of properties that will be passed to the associated {@link org.atmosphere.runtime.AtmosphereHandler}
     */
    String[] properties() default {};

    /**
     * Set to true if this {@link org.atmosphere.runtime.AtmosphereHandler} supports sessions
     *
     * @return true if session are supported.
     */
    boolean supportSession() default false;

    /**
     * Atmosphere's configuration that will be passed to the associated {@link org.atmosphere.runtime.AtmosphereHandler}. Configuration
     * name and value is delimited by "=", and different configuration lines are separated by comma.
     */
    String[] atmosphereConfig() default {};

    /**
     * A list of {@link org.atmosphere.runtime.AtmosphereInterceptor} to install
     */
    Class<? extends AtmosphereInterceptor>[] interceptors() default {};

    /**
      * The {@link org.atmosphere.runtime.BroadcasterCache} class name. By default, a no-ops {@link DefaultBroadcasterCache}
      * is installed. It is strongly recommended to install the {@link org.atmosphere.cache.UUIDBroadcasterCache} to prevent
      * message being lost.
      *
      * @return The {@link org.atmosphere.runtime.Broadcaster} class name
      */
     Class<? extends BroadcasterCache> broadcasterCache() default DefaultBroadcasterCache.class;

    /**
     * Add {@link org.atmosphere.runtime.AtmosphereResourceEventListener} to track internal events.
     */
    public Class<? extends AtmosphereResourceEventListener>[] listeners() default {};
}
