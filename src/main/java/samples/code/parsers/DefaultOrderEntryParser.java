package samples.code.parsers;

import samples.code.entities.OrderEntry;

public class DefaultOrderEntryParser implements OrderEntryParser {

	@Override
	public OrderEntry parse(String orderEntry, final String separator) {
		
		final String[] tokens = orderEntry.split(separator);
		
		return new OrderEntry(tokens[0], tokens[1]);
		
	}
	


}
