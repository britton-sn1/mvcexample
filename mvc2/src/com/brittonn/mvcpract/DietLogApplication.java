package com.brittonn.mvcpract;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.JerseyResourceContext;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Class used to initialise the jax-rs library for the restful web-services api
 * @author N Britton
 *
 */
public class DietLogApplication extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public DietLogApplication() {
		
		register(RequestContextFilter.class);
		register(JerseyResourceContext.class);

		// Jackson JSON marshalling
		register(JacksonFeature.class);
	}
}
