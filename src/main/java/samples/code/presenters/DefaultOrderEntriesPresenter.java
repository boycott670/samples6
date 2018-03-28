package samples.code.presenters;

import java.util.List;
import java.util.stream.Collectors;

import samples.code.entities.OrderEntry;

public class DefaultOrderEntriesPresenter implements OrderEntriesPresenter {

	@Override
	public String present(final List<OrderEntry> entries) {

		return entries.stream()
			.map(entry -> entry.getRecipe() + (entry.getQuantity() == 1 ? "" : " for " + entry.getQuantity()))
			.collect(Collectors.joining(", "));
		
	}

	@Override
	public String errorMessage(int missingOrder)
	{
		return "MISSING " + missingOrder;
	}

	@Override
	public String errorMessage(int missingOrder, String recipe, int quantity) {
		
		return String.format("MISSING %d for %s for %d", missingOrder, recipe, quantity);
		
	}
}
