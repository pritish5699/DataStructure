package com.pshah.ds.hashmap;

import java.util.LinkedList;

public class Hash<K, V> {
	
	private final int MAX_SIZE = 10;
	LinkedList<Cell<K, V>>[] items;
	
	@SuppressWarnings("unchecked")
	public Hash(){
		items = (LinkedList<Cell<K,V>>[])new LinkedList[MAX_SIZE];
	}
	
	
	public int hashCode(K key){
		return key.toString().length() % items.length;
	}
	
	public void put(K key, V value){
		int hash = hashCode(key);
		
		if(items[hash] == null)
			items[hash] = new LinkedList<Cell<K,V>>();
		
		LinkedList<Cell<K, V>> collided = items[hash];
		
		for(Cell<K, V> c: collided){
			if(c.equivalent(key)){
				collided.remove(c);
				break;
			}
			Cell<K, V> cell = new Cell<K, V>(key, value);
			collided.add(cell);
		}
	}
	
	public V get(K key){
		int x = hashCode(key);
		
		if(items[x] == null)
			return null;
		
		LinkedList<Cell<K, V>> collied = items[x];
		
		for(Cell<K, V> c : collied){
			
			if(c.equals(key))
				return c.getValue();
		}
		
		return null;
	}
}
