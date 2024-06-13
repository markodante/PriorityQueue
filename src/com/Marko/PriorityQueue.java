package com.Marko;

/**
 * @author Marko Orlando
 * @Date 05/30/2024
 * Navigating the Kafkaesque Healthcare System with a Queue and Custom Patient Class
 */

import java.util.Objects;

public class PriorityQueue {

	/**
	 * Main Method
	 * @param args Command line arguments
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
	    PriorityQueue hospitalLineup = new PriorityQueue();

	    hospitalLineup.enqueue("Habib", 9000);
	    hospitalLineup.enqueue("Laura", 4);
	    hospitalLineup.enqueue("George", 10);
	    hospitalLineup.enqueue("Linda", 9);

	    System.out.println(hospitalLineup);
	    System.out.println("The highest priority patient is: " + hospitalLineup.getHighestPriorityPatient());

	    String next = hospitalLineup.dequeue();
	    System.out.println(next + " has been dequeued");
	    hospitalLineup.enqueue("GiJoe", 10_000_000);
	    System.out.println("The highest priority patient is now: " + hospitalLineup.getHighestPriorityPatient());

	}

	private WLinkedList<Patient> queue;
	private Patient highestPriority;

	public PriorityQueue() {
		this.queue = new WLinkedList<Patient>();
		this.highestPriority = new Patient();
	}

	public void enqueue(String name, int priority) {

		// Create the patient with user inputed names and priority
		Patient patient = new Patient(name, priority);

		// Add this patient to the front of the queue
		this.queue.addToBackSlow(patient);

		// If there is no highest priority patient yet, or the new patient has a higher
		// priority than the current highest priority patient, then the new patient
		// becomes the highest priority patient
		if (this.highestPriority.isEmpty() || patient.priority() > this.highestPriority.priority()) {
			this.highestPriority = patient;
		}

	}

	public String dequeue() throws Exception {
		// Save frontmost Patient name
		String name = this.queue.lookAtFront().name();

		// Check if next patient in line is the highest priority patient and if so,
		// delete the highest priority patient
		if (this.queue.lookAtFront().equals(highestPriority)) {
			this.highestPriority.delete();
		}
		this.queue.removeFromFront();

		// Return that frontmost Patient name
		return name;

	}

	/**
	 * Determines the patient at the front of the queue
	 * 
	 * @return The patient at the front of the queue
	 * @throws Exception
	 */
	public String peek() throws Exception {
		return this.queue.lookAtFront().name();
	}

	/**
	 * Determines the highest priority patient
	 * 
	 * @return The highest priority patient
	 */
	public String getHighestPriorityPatient() {
		return this.highestPriority.name();
	}

	/**
	 * Determines whether the queue is empty or not
	 * 
	 * @return True or false
	 */
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}

	/**
	 * Determines the size of the queue
	 * 
	 * @return The size of the queue
	 */
	public int size() {
		return this.queue.size();
	}

	/**
	 * Prints out the contents of the queue as a standard list
	 * 
	 * @return The contents of the queue in the format "[<element>, <element>, ...]"
	 */
	public String toString() {
		return this.queue.toString();
	}

}

class Patient {
	private String name;
	private int priority;
	private boolean isEmpty;

	public Patient() {
		this.name = "";
		this.priority = -1;
		this.isEmpty = true;
	}

	public Patient(String first, int second) {
		this.name = first;
		this.priority = second;
		this.isEmpty = false;
	}

	/**
	 * Determines the name of the patient
	 * 
	 * @return the name of the patient
	 */
	public String name() {
		return this.name;
	}

	/**
	 * Determines the level of priority with respect to
	 * 
	 * @return the level of priority
	 */
	public int priority() {
		return this.priority;
	}

	/**
	 * Deletes the priority level fo a patient
	 */
	public void delete() {
		this.priority = -1;
		this.isEmpty = true;
	}

	/**
	 * Checks whether the queue is empty
	 * 
	 * @return true or false
	 */
	public boolean isEmpty() {
		return isEmpty;
	}

	@Override
	/**
	 * Compares two parients to see whether they are equal (in terms of priority
	 * 
	 * @param obj The object that is to be compared (the patient)
	 */
	public boolean equals(Object obj) {
		// If the object contains nothing, no equating can be done
		if (obj == null)
			return false;
		// If the object contains something, but not a patient, no equating can be done
		if (!(obj instanceof Patient))
			return false;
		// If the object is a patient, cast obj to a Patient. Then, compare the patients in terms of 
		Patient o = (Patient) obj;
		return Objects.equals(name, o.name) && Objects.equals(this.priority, o.priority);
	}

	@Override
	/**
	 * Prints out the data associated with the patient
	 * 
	 * @return The contents of the patient in the format "<(name), (priority)>"
	 */
	public String toString() {
		return "<" + this.name + ", " + this.priority + ">";
	}

}