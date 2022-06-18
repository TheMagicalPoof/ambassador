package com.magicalpoof.ambassador;

import java.util.ArrayList;
import java.util.List;


public class Admission {
	private static Admission instance;
	public Admission() {
		instance = this;
	}
	
	public synchronized Admission getInstance() {
        if (instance == null) init();
        return instance;
    }
	
	static void init() {
        instance = new Admission();  
    }
	
	static List<String> list = new ArrayList<String>();
	
	public static boolean isFree() {
		return !list.isEmpty();		 
	}
	
	public static List<String> getList() {
		return list;
	}
	
	
//	Bukkit.broadcastMessage(ChatColor.GREEN + "Амбассадор " + joinEv.getPlayer().getName() + " зашёл на сервер. Белый лист отключён.");
	
}
