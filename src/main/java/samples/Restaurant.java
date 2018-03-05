package samples;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import samples.entities.Client;
import samples.entities.OrderEntry;
import samples.parsers.DefaultOrderParser;
import samples.parsers.OrderParser;
import samples.presenters.DefaultTablePresenter;
import samples.presenters.TablePresenter;

public class Restaurant
{
	private OrderParser orderParser = new DefaultOrderParser();
	private TablePresenter tablePresenter = new DefaultTablePresenter();

	private Integer nextTableId;
	private Map<Integer, Table> tables;
	
	public Restaurant()
	{
		nextTableId = 1;
		tables = new HashMap<>();
	}
	
	public Integer initTable (final Integer maxTableOrders)
	{
		tables.put(nextTableId, new Table(maxTableOrders));
		return nextTableId ++;
	}
	
	public void customerSays (final Integer tableId, final String order)
	{
		final Entry<Client, OrderEntry> parsedOrder = orderParser.parse(order);
		
		tables.get(tableId).addOrder(parsedOrder.getKey(), parsedOrder.getValue());
	}
	
	public String createOrder (final Integer tableId)
	{
		final String validationMessage = tables.get(tableId).validateTable();
		
		if (validationMessage == null)
		{
			return tables.get(tableId).present(tablePresenter);
		}
		
		return validationMessage;
	}
}
