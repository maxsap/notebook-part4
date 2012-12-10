package com.openshift.notebook.core.cache;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import org.infinispan.api.BasicCache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.spring.provider.SpringCache;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;

public class InfinispanCacheManager implements CacheManager {

	private final Object nativeCacheManager;

	/**
	 * @param nativeCacheManager
	 */
	public InfinispanCacheManager(final Object nativeCacheManager) {
		Assert.notNull(nativeCacheManager,
				"A non-null instance of EmbeddedCacheManager needs to be supplied");
		this.nativeCacheManager = nativeCacheManager;
	}

	@Override
	public Cache getCache(final String name) {
		try{
			Method method = nativeCacheManager.getClass().getMethod("getCache",String.class);
			Map<Object, Object> cache = (Map) method.invoke(nativeCacheManager,name);
			
//			Method getNameMethod = cache.getClass().getMethod("getName");
//			String name = (String)getNameMethod.invoke(cache);
			return new InfinispanSpringCache(cache,name);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public Collection<String> getCacheNames() {
		try {
			Method method = nativeCacheManager.getClass().getMethod("getCacheNames");
			return (Collection<String>)method.invoke(nativeCacheManager);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Return the {@link org.infinispan.manager.EmbeddedCacheManager
	 * <code>org.infinispan.manager.EmbeddedCacheManager</code>} that backs this
	 * <code>CacheManager</code>.
	 * 
	 * @return The {@link org.infinispan.manager.EmbeddedCacheManager
	 *         <code>org.infinispan.manager.EmbeddedCacheManager</code>} that
	 *         backs this <code>CacheManager</code>
	 */
	public Object getNativeCacheManager() {
		return this.nativeCacheManager;
	}

	/**
	 * Stop the {@link EmbeddedCacheManager <code>EmbeddedCacheManager</code>}
	 * this <code>CacheManager</code> delegates to.
	 */
	public void stop() {
		try {
			Method method = this.nativeCacheManager.getClass().getMethod("stop");
			method.invoke(this.nativeCacheManager);	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		

	}
}