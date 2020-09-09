package com.nogroup.booster.processors;

import java.lang.reflect.Method;

import com.nogroup.booster.annotations.EntryPoint;
import com.nogroup.booster.annotations.Process;
import com.nogroup.booster.annotations.ProcessContainer;
import com.nogroup.booster.utils.EndPoint;
import com.nogroup.booster.utils.Work;

public class FunctionalProcessor {

	
	public void run(final Class<?> cls) throws NoSuchMethodException, SecurityException {
		
		final EntryPoint[] vl = cls.getAnnotationsByType(EntryPoint.class) ;
		if(vl.length == 0) {
			System.err.println("Please Specify An Entry Point For " + cls.getSimpleName());
			return ;
		}
		
		ProcessContainer[] vll = cls.getAnnotationsByType(ProcessContainer.class) ;
		if(vll.length == 0) {
			System.err.println("Please Add ProcessContainer Annotation To " + cls.getSimpleName());
			return ;
		}
		
		new Work(vll[0].label(),0,!vll[0].label().equals("")) {
			
			@Override
			public void function() throws Exception{
				runProcess(cls.newInstance(),cls.getMethod(vl[0].value())) ;
			}
		}.withDelay(delay()).run();
		
		if(vll[0].next() != EndPoint.class) {
			run(vll[0].next());
		}
		
	}

	private void runProcess(final Object obj, final Method method) {
		
		final Process[] vl = method.getAnnotationsByType(Process.class) ;
		if(vl.length == 0) {
			System.err.println("Please Annotate The Entry Point " + method.getName() + " in Class " + obj.getClass().getSimpleName());
			return ;
		}
		
		new Work(vl[0].label(),0,!vl[0].label().equals("")) {
			
			@Override
			public void function() throws Exception{
				
				method.invoke(obj, null) ;
				
				if(vl[0].next().equals("")) {
					return ;
				}
				Method mth = obj.getClass().getMethod(vl[0].next()) ;
				if(mth != null) {
					FunctionalProcessor.this.runProcess(obj,mth);
				}
			}
		}.withDelay(delay()).run();
	}
	
	public int delay() {
		return 100 ;
	}
}
