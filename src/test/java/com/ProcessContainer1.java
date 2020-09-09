package com;

import com.nogroup.booster.annotations.EntryPoint;
import com.nogroup.booster.annotations.Process;
import com.nogroup.booster.annotations.ProcessContainer;

@ProcessContainer(label = "First Process Container", next = ProcessContainer2.class)
@EntryPoint("m1")
public class ProcessContainer1 {

	@Process(next = "m2",label="First Process")
	public void m1() {
		System.out.println("First Process");
	}
	
	@Process(label="Second Process")
	public void m2() {
		System.out.println("Second Process");
	}

}
