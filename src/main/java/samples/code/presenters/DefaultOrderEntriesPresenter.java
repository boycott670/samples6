package samples.code.presenters;

import java.util.List;
import java.util.stream.Collectors;

import samples.code.entities.OrderEntry;

public class DefaultOrderEntriesPresenter implements OrderEntriesPresenter {

	@Override
	public String present(final List<OrderEntry> entries) {

		return entries.stream().map(OrderEntry::getRecipe).collect(Collectors.joining(", "));
		
	}

	@Override
	public String errorMessage(int missingOrder)
	{
		return "MISSING " + missingOrder;
	}

	
	
}
