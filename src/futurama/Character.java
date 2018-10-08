package futurama;

public class Character<T>  {
	protected T mind;
	protected T body;
	
	public Character(T person) {
		this.mind = this.body = person;
	}
	
	public Character(T brain, T body) {
		this.mind = brain;
		this.body = body;
	}

	public T getMind() {
		return mind;
	}

	public void setMind(T mind) {
		this.mind = mind;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
	public String toString() {
		return "Body: ".concat(body.toString()).concat(", Mind: ").concat(mind.toString());
	}
	
	public Character<T> clone() {
		return new Character<T>(this.mind, this.body);
	}

}
