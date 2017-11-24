package com.pshah.ds.hashmap;

public class Cell<K, V> {
	
	private K key;
	private V val;
	
	public Cell(K key, V val){
		this.key = key;
		this.val = val;
	}
	
	public K getKey(){
		return key;
	}
	
	public V getValue(){
		return val;
	}
	
	public boolean equivalent(K key){
		return this.key.equals(key);
	}
	
	public boolean equivalent(Cell<K, V> c){
		return equals(c.key);
	}
}
