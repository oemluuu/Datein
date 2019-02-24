package com.easydsgn.bot.handlers;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.easydsgn.bot.Start;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class TimeHandler {
	
	public static final long startTime = System.currentTimeMillis();
	public static long currentTime;
	public static long currentUserTime;
	public static long rightTime;
	public static final long twoWeeks = 1209600000;
	
	public static HashMap<String, Long>  hm = new HashMap<String, Long>();
	public static Client[] userArray;
	
	public static ArrayList<String> toRemove = new ArrayList<String>();
	
	public static long time;
	public static final long day = 86400000;
	
	public static long period = FileHandler.period * 1000;
	
	public TimeHandler() {	
		check();
		System.out.println("#######################################################");
		giveServerGroup();
		
		
	}
	
	public static void check() 
	{
		currentUserTime = System.currentTimeMillis();
		time = currentUserTime + period;
		userArray = Start.api.getClients().toArray(new Client[0]);
		for(int i = 0; i < userArray.length; i++) 
		{
			if(userArray[i] != null) {
				if(userArray[i].isInServerGroup(FileHandler.group_eins)) {
					System.out.println("[!] CHECK 1 WIRD AUSGEFUEHRT");
					if(!userArray[i].isInServerGroup(FileHandler.group_zwei)) 
					{
						System.out.println("[!] CHECK 2 WIRD AUSGEFUEHRT");
						if(!hm.containsKey(userArray[i].getUniqueIdentifier())) 
						{
							if(userArray[i] != null) {
								System.out.println("[!] MEMBER " + userArray[i].getNickname() +" HINZUGEFUEGT!");
								hm.put(userArray[i].getUniqueIdentifier(), time);
								System.out.println(hm);
							}
						}
					}
				}
			}

		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public static void giveServerGroup() {
		
		for(Map.Entry<String, Long> entry : hm.entrySet()) {
			try{
				if(Start.api.getDatabaseClientByUId(entry.getKey()) != null) {
					System.out.println("[!] JETZTIGER MEMBER: " + Start.api.getDatabaseClientByUId(entry.getKey()).getNickname());
					if(hm.containsKey(entry.getKey())) {
						System.out.println("[!] ZEIT WIRD UEBERPRUEFT");
						if(entry.getValue() <= System.currentTimeMillis()) {
							if(Start.api.getClientByUId(entry.getKey()) != null) {
								if(!Start.api.getClientByUId(entry.getKey()).isInServerGroup(FileHandler.group_zwei)) {
									System.out.println("[++] USER " + Start.api.getClientByUId(entry.getKey()).getNickname() + " WURDE DIE SERVERGRUPPE " + FileHandler.group_zwei + " HINZUGEFUEGT!");
									Start.api.addClientToServerGroup(FileHandler.group_zwei, Start.api.getDatabaseClientByUId(entry.getKey()).getDatabaseId());
									Start.api.removeClientFromServerGroup(FileHandler.group_eins, Start.api.getDatabaseClientByUId(entry.getKey()).getDatabaseId());
									toRemove.add(entry.getKey());
								}
							}else {
								System.out.println("[!]DER MEMBER " + Start.api.getDatabaseClientByUId(entry.getKey()).getNickname() + " IST NICHT ONLINE");
								toRemove.add(entry.getKey());
							}
						}
					}
				}else {
					System.out.println("[!]Dieser Client ist nicht in der Database");
					toRemove.add(entry.getKey());
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static void remove() {
		System.out.println("[-] LOESCHUNG");
		
		Iterator<String> it = toRemove.iterator();
		System.out.println(toRemove);
		
		while(it.hasNext()) {
			hm.remove(it.next());
			it.remove();
		}
	}

}
