package samples.code.presenters;

import java.util.List;

import samples.code.entities.OrderEntry;

public interface OrderEntriesPresenter {

	String present (final List<OrderEntry> entries);
	String errorMessage (final int missingOrder);
	
}
