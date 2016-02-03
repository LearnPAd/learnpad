package eu.learnpad.qm.component;

public class QuestionnaireGenerationTask implements Runnable {

	private String genProcessID;
	private volatile QuestionnaireGenerationStatus generationStatus;
	private QuestionnaireManagerNotifier notifier;
	
	private final String DEFAULT_FILE_PATH_TO_RETURN = "/tmp/foo.out";
	
	public QuestionnaireGenerationTask(String genProcessID, QuestionnaireManagerNotifier notifier) {
		this.genProcessID = genProcessID;
		this.generationStatus = QuestionnaireGenerationStatus.NotAvailable;
		this.notifier = notifier;
	}
	
	@Override
	public void run() {
		synchronized (this) {
			this.generationStatus = QuestionnaireGenerationStatus.InProgress;
		}	
		this.invokeMOTHIA();
		this.notifier.generationCompleted(this.genProcessID, this.DEFAULT_FILE_PATH_TO_RETURN);
	}

	public String getGenerationProcessID(){
		return this.genProcessID;
	}
	
	public synchronized QuestionnaireGenerationStatus getGenerationStatus(){
		return this.generationStatus;
	}

	/**
	 * THIS METHOD IS FOR TESTING PURPOSE ONLY.
	 * IT WILL BE DELETED WHEN THE INTEGRATION WITH MOTHIA WILL BE COMPLETED
	 * DO NOT USE IT!!!
	 */
	public void stimulusFromABackdoor(){
		synchronized (this) {
			switch (this.generationStatus) {
			case InProgress:
				this.generationStatus = QuestionnaireGenerationStatus.Completed;
				break;
			case Completed:
				break;
			default:
				this.generationStatus = QuestionnaireGenerationStatus.NotAvailable;
				break;
			}
			// It wakes up all the threads waiting due to the invokeMOTHIA method				
			this.notifyAll();
		}	
	}
	
	private void invokeMOTHIA(){
		// TODO Link the generation process with MOTHIA
		// this is only stub code
		synchronized (this) {
			while (this.generationStatus != QuestionnaireGenerationStatus.Completed){
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
	}
}


















