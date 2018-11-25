
public class List {
	
	private Object[] list;
	
	private int size;
	
	public List(){
		size = 0;
	}
	
	public void add(Object toAdd){
		Object[] newList = new Object[size + 1];
		for (int i = 0; i < size; i += 1){
			newList[i] = list[i];
		}
		newList[size] = toAdd;
		list = newList;
		size += 1;
	}
	
	public void remove(int index){
		Object[] newList = new Object[size - 1];
		for (int i = 0; i < index; i += 1){
			newList[i] = list[i];
		}
		size -= 1;
		for (int i = index; i < size; i += 1){
			newList[i] = list[i + 1];
		}
		list = newList;
	}
	
	public int indexOf(Object toFind){
		for (int i = 0; i < size; i += 1){
			if (list[i].equals(toFind)){
				return i;
			}
		}
		return -1;
	}
	
	public Object get(int index) throws IndexOutOfBoundsException{
		try{
			return list[index];
		}
		catch(IndexOutOfBoundsException e){
			return e;
		}
	}
	
	public boolean contains(Object toFind){
		for (int i = 0; i < size; i += 1){
			if (list[i].equals(toFind)){
				return true;
			}
		}
		return false;
	}
	
	public int size(){
		return size;
	}

}
