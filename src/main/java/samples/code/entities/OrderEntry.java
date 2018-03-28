package samples.code.entities;

public class OrderEntry {

	private final String client;
	private final String recipe;
	private final int quantity;
	
	public OrderEntry(String client, String recipe, final int quantity) {
		this.client = client;
		this.recipe = recipe;
		this.quantity = quantity;
	}

	public String getClient() {
		return client;
	}

	public String getRecipe() {
		return recipe;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderEntry other = (OrderEntry) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		return true;
	}
	
	
	
}
