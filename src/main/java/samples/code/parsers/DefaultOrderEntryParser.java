package samples.code.parsers;

import samples.code.entities.OrderEntry;

public class DefaultOrderEntryParser implements OrderEntryParser {

	@Override
	public OrderEntry parse(String orderEntry, final String separator) {
		
		final String[] clientSplit = orderEntry.split(separator);
		final String[] recipeSplit = clientSplit[1].split(" for ");
		
		return new OrderEntry(clientSplit[0], recipeSplit[0], recipeSplit.length == 1 ? 1 : Integer.valueOf(recipeSplit[1]));
		
	}
	


}
