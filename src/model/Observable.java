package model;

import java.util.ArrayList;

public abstract class Observable implements IObservable {
	
	protected ArrayList<IObserver> observers;
	
	public Observable() {
		this.observers = new ArrayList<IObserver>();
	}
	
	@Override
	public void addObserver(IObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(IObserver observer) {
		this.observers.remove(observer);
	}

	@Override
	public void removeAllObservers() {
		for (IObserver observer : this.observers) {
			this.removeObserver(observer);
		}
	}

	@Override
	public void notifyObserver(IObserver observer) {
		observer.update();
	}

	@Override
	public void notifyAllObservers() {
		for (IObserver observer : this.observers) {
			this.notifyObserver(observer);
		}
	}
	
}
