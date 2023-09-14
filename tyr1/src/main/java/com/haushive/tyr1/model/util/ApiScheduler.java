package com.haushive.tyr1.model.util;

import java.util.Timer;
import java.util.TimerTask;

public class ApiScheduler extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			scheduler();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    public void scheduler() throws InterruptedException {
    	TimerTask timerTask = new TimerTask() {
    		public void run() {
              
            }
    	};
    	Timer timer = new Timer();
    	timer.schedule(timerTask, 10000);
    }

}
