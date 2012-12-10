package com.openshift.notebook.core.cache;

import java.util.Map;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.Assert;

public class InfinispanSpringCache implements Cache {

   private final Map<Object, Object> nativeCache;
   private final String name;

   /**
    * @param nativeCache
 * @param name 
    */
   public InfinispanSpringCache(final Map<Object, Object> nativeCache, String name) {
      Assert.notNull(nativeCache, "A non-null Infinispan cache implementation is required");
      this.nativeCache = nativeCache;
      this.name = name;
   }

   /**
    * @see org.springframework.cache.Cache#getName()
    */
   @Override
   public String getName() {
      return name;
   }

   /**
    * @see org.springframework.cache.Cache#getNativeCache()
    */
   @Override
   public Map<?, ?> getNativeCache() {
      return this.nativeCache;
   }

   /**
    * @see org.springframework.cache.Cache#get(java.lang.Object)
    */
   @Override
   public ValueWrapper get(final Object key) {
      Object v = nativeCache.get(key);
	  return (v != null ? new SimpleValueWrapper(v) : null);
   }

   /**
    * @see org.springframework.cache.Cache#put(java.lang.Object, java.lang.Object)
    */
   @Override
   public void put(final Object key, final Object value) {
      this.nativeCache.put(key, value);
   }

   /**
    * @see org.springframework.cache.Cache#evict(java.lang.Object)
    */
   @Override
   public void evict(final Object key) {
     this.nativeCache.remove(key);
   }


   /**
    * @see org.springframework.cache.Cache#clear()
    */
   @Override
   public void clear() {
      this.nativeCache.clear();
   }

   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return "InfinispanCache [nativeCache = " + this.nativeCache + "]";
   }

}
