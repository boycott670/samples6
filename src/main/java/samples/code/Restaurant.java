package samples.code;

import java.util.HashMap;
import java.util.Map;

import samples.code.entities.Table;
import samples.code.parsers.DefaultOrderEntryParser;
import samples.code.parsers.OrderEntryParser;

public class Restaurant {
	
	private final OrderEntryParser parser = new DefaultOrderEntryParser();
	private int tableIndex = 0;
	private final Map<Integer, Table> tables = new HashMap<>();
	
	public synchronized int initTable (int size)
	{
		tables.put(++tableIndex, new Table(size));
		return tableIndex;
	}

	public void customerSays(final int tableId, final String orderEntry) {
		
		tables.get(tableId).addOrderEntry(orderEntry, ": ", parser);
		
	}

	public String createOrder(final int tableId) {

		return tables.get(tableId).present();
		
	}

}
