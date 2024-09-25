package pc.iter;

import pc.IList;

public class SimpleListFine<T> implements IList<T> {
	private Chainon<T> head; // Premier élément de la liste

	private static class Chainon<T> {
		T data; // Donnée du chaînon
		Chainon<T> next; // Référence au chaînon suivant

		public Chainon(T data) {
			this.data = data;
			// NB : next est null par défaut
		}

	}
	
	public SimpleListFine() {
		head = null;
	}
	
	@Override
	public int size() {
		int size = 0;
		
		synchronized (this) {
		Chainon<T> cur = head;
		}
		
		while (cur != null) {
			synchronized(this) {
				size++;
				cur = cur.next;
			}
		}
		return size;
	}

	@Override
	public void add(T element) {
		if (head == null) {
			head = new Chainon<>(element);
			return;
		}
		
		Chainon<T> cur;
		synchronized (this) {
			cur = head;
		}
		while(cur!=null) {
			synchronized(cur) {
				if (cur.next == null) {
					cur.next = new Chainon<>(element);
					break;
				}
				cur = cur.next;
			}
		}
	}

	@Override
	public boolean contains(T element) {
		for (Chainon<T> cur = head; cur != null; cur = cur.next) {
			if (cur.data.equals(element)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		synchronized(this) {
		head = null;
		}
		// NB : grace au gc, les éléments de la liste sont supprimés
		// dans d'autres langages, il faudrait les supprimer un par un (e.g. C++)
	}


}


}
