package model;

public interface IObservable {
	void addObserver(IObserver observer);
	void removeObserver(IObserver observer);
	void removeAllObservers();
	void notifyObserver(IObserver observer);
	void notifyAllObservers();
}
