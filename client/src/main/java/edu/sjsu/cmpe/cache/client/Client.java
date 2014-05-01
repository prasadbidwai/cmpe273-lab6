package edu.sjsu.cmpe.cache.client;
import java.util.ArrayList;
import java.util.List;
import com.google.common.hash.Hashing;

public class Client {
	
	

    public static void main(String[] args) throws Exception {
        
    	int bucket;
    	
    	 
    	List<CacheServiceInterface> server = new ArrayList<CacheServiceInterface>();
    	 	server.add(new DistributedCacheService("http://localhost:3000"));
    	 	server.add(new DistributedCacheService("http://localhost:3001"));
    	 	server.add(new DistributedCacheService("http://localhost:3002"));
    	
    	 	System.out.println("Starting Cache Client...");
        
    	
    	char value = 'a';
    	
    	for(int i=1;i<=10;i++)
    	{
    		bucket = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(i)), server.size());
     		server.get(bucket).put(i, Character.toString(value));
     		System.out.println("(" + i +" ==> "+ value + ")");
     					value++;
     	}
    	 		
    	for(int i=1;i<=10;i++)
    	{
    		bucket = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(i)), server.size());
    		System.out.println("(" + i + "==>" + server.get(bucket).get(i) +")");
    	}
    	
    	 		System.out.println("Existing Cache Client...");
       
        
		
    }

}
