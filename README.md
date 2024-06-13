# PriorityQueue

This project is meant to mimic the queue system a standard hospital uses. Every patient added to the queue has a name and associated level of priority. If a patient is in more dire need of treatment, they will have a higher priority level. After patients are treated, they can be removed from the queue, and whichever patient has the next highest priority level becomes the new highest priority patient.

The project is also dependent on the WLinkedList class, which is a generic type linked list. PriorityQueue uses WLinkedList to create its queue

## Resources

Used GPT to enhance comments for the public boolean equals(Object obj) method in PriorityQueue. My prompt was:

Explain how the following code is able to compare two Patient objects:
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Patient))
			return false;
		Patient o = (Patient) obj;
		return Objects.equals(name, o.name) && Objects.equals(this.priority, o.priority);
	}
