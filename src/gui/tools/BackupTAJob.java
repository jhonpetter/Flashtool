package gui.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.logger.LogProgress;

import flashsystem.X10flash;

public class BackupTAJob extends Job {

	X10flash flash = null;
	boolean canceled = false;
	static final Logger logger = LogManager.getLogger(BackupTAJob.class);

	public BackupTAJob(String name) {
		super(name);
	}
	
	public void setFlash(X10flash f) {
		flash=f;
	}
	
    protected IStatus run(IProgressMonitor monitor) {
    	try {
			flash.openDevice();
			flash.sendLoader();
			flash.BackupTA();
			flash.closeDevice();
			logger.info("Dumping TA finished.");
			LogProgress.initProgress(0);
			return Status.OK_STATUS;
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		LogProgress.initProgress(0);
    		return Status.CANCEL_STATUS;
    	}
    }
}