package study.quartz.test;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;  

public class QuartzTest {
	
	public static void main(String[] args) {

		try {
			SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

			Scheduler sched = schedFact.getScheduler();

			sched.start();

			// define the job and tie it to our HelloJob class
			JobDetail job = newJob(HelloJob.class).withIdentity("myJob1", "group1").build();

			// Trigger the job to run now, and then every 40 seconds
			Trigger trigger = newTrigger().withIdentity("myTrigger", "group1").startNow()
					.withSchedule(simpleSchedule().withIntervalInSeconds(40).repeatForever()).build();
			// Tell quartz to schedule the job using our trigger
			//sched.scheduleJob(job, trigger);

			JobDetail dumbJob = newJob(DumbJob.class)
					.withIdentity("myJob2", "group1") // name
																						// "myJob",
																						// group
																						// "group1"
					.usingJobData("jobSays", "Hello World!")
					.usingJobData("myFloatValue", 3.141f)
					.build();
			sched.scheduleJob(dumbJob, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}  
	
	}
	

	
}
