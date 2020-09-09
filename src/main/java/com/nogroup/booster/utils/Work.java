package com.nogroup.booster.utils;

import java.util.concurrent.TimeUnit;

public abstract class Work {

	private String label ;
	private int indent = 0 ;
	private boolean decorate = true;
	private int delay = 100;
	
	public Work(String label) {
		super();
		this.label = label;
	}
	
	public Work(String label,int indent) {
		super();
		this.label = label;
		this.indent = indent ;
	}
	
	public Work(String label,int indent,boolean decorate) {
		super();
		this.label = label;
		this.indent = indent ;
		this.decorate  = decorate ;
	}
	public abstract void function() throws Exception ;
	
	public Work withDelay(int val) {
		
		delay  = val ;
		return this ;
	}
	public void run() {
		if(decorate) {
			System.out.println(indent() + "[INFO] " + label);
		}
		try {
			TimeUnit.MILLISECONDS.sleep(delay);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			function();
		}catch (Exception e) {
			if(decorate) {
				System.out.println(ConsoleColors.RED + indent() + "[ERROR] " + e.getCause().getMessage() + ConsoleColors.RESET);
			}
			e.printStackTrace();
			System.exit(0); 
		}
		
	}
	
	public String indent() {
		String ss = " " ;
		for(int i = 0 ; i < indent;i++) {
			ss += "\t" ;
		}
		return ss ;
	}
	
}
