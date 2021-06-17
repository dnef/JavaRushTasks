package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever{
    Storage storage;
    OriginalRetriever originalRetriever;
    LRUCache lruCache;

    public CachingProxyRetriever(Storage storage) {
        this.storage = storage;
        this.originalRetriever = new OriginalRetriever(storage);
        this.lruCache = new LRUCache(16);
    }
    @Override
    public Object retrieve(long id) {
        if (lruCache.find(id) !=null){
            return lruCache.get(id);
        }else{
            Object lru = originalRetriever.retrieve(id);
            lruCache.set(id,lru);
            return lru;
        }
        //return null;
    }
}
