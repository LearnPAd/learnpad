package eu.learnpad.simulator.mon.services;

public interface ServiceRegistry {

	public void add(ChoreosService svc);
	public void remove(ChoreosService svc);
	public ChoreosService getElementAt(int index);
	public void removeElementAt(int index);
	public void clear();
	public void setDimension(int newSize);
	public int getSize();	
}
