package edu.psu.se411.lab12_threads.model;
import java.util.concurrent.locks.ReentrantLock;
import edu.psu.se411.lab12_threads.exceptions.InsufficientBalanceException;

public class CryptoAccount {
	private String id;
	private Double balance;
	
	public CryptoAccount(String id, Double balance) {
		if(balance < 0) throw new IllegalArgumentException("Account balance cannot be negative");
		this.id = id;
		this.balance = balance;
	}
	
	public  void addToBalance(Double amount) {
		if(amount < 0) throw new IllegalArgumentException("Amount added to balance cannot be negative");
		balance += amount;
	}
	
	public  void retrieveFromBalance(Double amount) throws InsufficientBalanceException {
		if(amount > balance) throw new InsufficientBalanceException(id);
		balance -= amount;
	}
	
	public String getId() {
		return id;
	}
	public Double getBalance() {
		return balance;
	}
	private final ReentrantLock lock = new ReentrantLock();
	
	public ReentrantLock getLock() {
	    return lock;
	}
}
