package com;

import com.nogroup.booster.annotations.EntryPoint;
import com.nogroup.booster.annotations.Process;
import com.nogroup.booster.annotations.ProcessContainer;

@ProcessContainer(label = "First Process Container")
@EntryPoint("m1")
public class ProcessContainer2 {

	@Process(next = "m2",label="First Process")
	public void m1() {
		System.out.println("First Process in 2");
	}
	
	@Process(label="Second Process")
	public void m2() {
		System.out.println("Second Process in 2");
	}

}
