package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
    // Инициализируем размер сегмента, то есть размер массива, размер по умолчанию - 16
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    // Максимальное значение ведра
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize;

    public static int hash(Long k) {
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    //Возвращает индекс для хэш-кода hash.
    public static int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    //Возвращает запись, связанную с указанным ключом
    public Entry getEntry(Long key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        if (table[index] != null) {
            Entry entry = table[index].getEntry();
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    return entry;
                }
            }
        }

        return null;
    }

    public void resize(int newCapacity) {
        FileBucket[] oldTable = table;
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    public void transfer(FileBucket[] newTable) {

        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();
            while (entry != null) {
                Entry entryNext = entry.next;
                int index = indexFor(entry.hash, newTable.length);
                if (newTable[index] == null) {
                    entry.next = null;
                    newTable[index] = new FileBucket();
                } else {
                    entry.next = newTable[index].getEntry();
                }
                newTable[index].putEntry(entry);
                entry = entryNext;
            }
            table[i].remove();
        }
    }
    public void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash,key,value,entry));
        size++;
        if (table[bucketIndex].getFileSize()>bucketSizeLimit){resize(2* table.length);}
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash,key,value,null));
        size++;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }
    //должен вернуть true, если хранилище содержит переданный ключ
    @Override
    public boolean containsKey(Long key) {
        return getEntry(key)!=null;
    }
    //должен вернуть true, если хранилище содержит переданное значение
    @Override
    public boolean containsValue(String value) {
        for (int i=0;i< table.length;i++){
            if (table[i]==null) continue;
            Entry entry = table[i].getEntry();
            while (entry!=null){
                if (entry.getValue().equals(value)){return true;}
                entry=entry.next;
            }
        }
        return false;
    }
    //добавить в хранилище новую пару ключ - значение
    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int indexHash =indexFor(hash, table.length);
        if (table[indexHash]!=null){
            Entry entry = table[indexHash].getEntry();
            while(entry!=null){
                if (entry.getKey().equals(key)){
                    entry.value=value;
                    return;
                }
                entry=entry.next;
            }
            addEntry(hash,key,value,indexHash);
        }else {
            createEntry(hash,key,value,indexHash);
        }
    }
    //вернуть ключ для переданного значения
    @Override
    public Long getKey(String value) {
        for (int i=0;i<table.length;i++){
            if (table[i]==null) continue;
            Entry entry = table[i].getEntry();
            while (entry!=null){
                if (entry.value.equals(value)){return entry.getKey();}
                entry=entry.next;
            }
        }
        return null;
    }
    //вернуть значение для переданного ключа
    @Override
    public String getValue(Long key) {
        Entry entry = getEntry(key);
        if (entry!=null) return entry.value;
        return null;
    }
}
