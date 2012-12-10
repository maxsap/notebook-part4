package com.openshift.notebook.core.config;

import javax.inject.Inject;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jndi.JndiTemplate;

import com.openshift.notebook.core.cache.InfinispanCacheManager;

@Configuration
@EnableCaching
@Profile("openshift")
public class InfinispanCacheConfig {

	@Inject
	private JndiTemplate jndiTemplate;

	@Bean
	public CacheManager cacheManager() throws Exception {
		Object nativeCacheManager = jndiTemplate.lookup("java:jboss/infinispan/cluster");
		return new InfinispanCacheManager(nativeCacheManager);
	}
}
