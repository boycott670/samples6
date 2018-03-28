package samples.code.parsers;

import samples.code.entities.OrderEntry;

public interface OrderEntryParser {

	OrderEntry parse (final String orderEntry, final String separator);
	
}
