package com.easydsgn.bot;

import java.awt.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import com.easydsgn.bot.events.TS3Events;
import com.easydsgn.bot.handlers.FileHandler;
import com.easydsgn.bot.handlers.TimeHandler;
import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.TS3Query.FloodRate;
import com.github.theholywaffle.teamspeak3.api.wrapper.ServerGroupClient;

public class Start {
	
	public static final TS3Config config = new TS3Config();
	public static final TS3Query query = new TS3Query();
	public static final TS3Api api = query.getApi();
	
	//public static final long currentTime = System.currentTimeMillis();
	//public static final long twoWeeks = 1209600000;
	
	public static TimeHandler handler;
	
	public static final long delay = 21000000;
	public static final long period = 600000;
	
	public static void main(String[] args) {
		FileHandler.handleIpAndPass();
		setupBot();
		System.out.println("[+] VERBINDUNG HERGESTELLT");
		System.out.println("[i] VERSUCHE ZU VERBINDEN");
		TS3Events.load();
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("[+]--------------------------------------------BEGIN!\n");
				System.out.println("\n");
				FileHandler.handleIpAndPass();
				FileHandler.handleFile();
				System.out.println("\n");
				TimeHandler.currentTime = System.currentTimeMillis();
				System.out.println("\n");
				TimeHandler.check();
				System.out.println("\n");
				TimeHandler.giveServerGroup();
				System.out.println("\n");
				TimeHandler.remove();
				FileHandler.save();
				System.out.println("\n");
				System.out.println("[-]--------------------------------------------END!");
				
			}
		}, 1000, 1000);
		
		
		System.out.println(api.getClients().size());
	}
	
	public static void setupBot() {
		config.setHost(FileHandler.ip);
		config.setFloodRate(FloodRate.UNLIMITED);
		query.connect();
		api.login(FileHandler.user, FileHandler.pass);
		api.selectVirtualServerById(FileHandler.id);
		api.setNickname(FileHandler.nick);
	}
}