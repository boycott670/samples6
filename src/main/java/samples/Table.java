package samples;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import samples.entities.Client;
import samples.entities.OrderEntry;
import samples.presenters.TablePresenter;

public class Table
{

	private Map<Client, OrderEntry> orders;
	private Integer maxOrders;
	private OrderEntry previousOrderEntry;
	
	public Table(Integer maxOrders)
	{
		this.maxOrders = maxOrders;
		orders = new LinkedHashMap<>();
	}
	
	public void addOrder (final Client client, final OrderEntry orderEntry)
	{
		if ("Same".equals(orderEntry.getContent()))
		{
			orderEntry.setContent(previousOrderEntry.getContent());
		}
		
		orders.put(client, orderEntry);
		previousOrderEntry = orderEntry;
	}
	
	public String validateTable ()
	{
		if (maxOrders - orders.size() == 0)
		{
			final Optional<Entry<Client, OrderEntry>> orderHavingQuantityGreaterThanOne =
				orders.entrySet()
					.stream()
					.filter(order -> order.getValue().getQuantity() > 1)
					.findFirst()
			;
			
			if (orderHavingQuantityGreaterThanOne.isPresent())
			{
				final long countOtherOrdersHavingQuantityGreaterThanOne =
					orders.entrySet()
						.stream()
						.filter(order -> !order.equals(orderHavingQuantityGreaterThanOne.get()) && order.getValue().getQuantity() > 1)
						.count()
				;
				
				if (countOtherOrdersHavingQuantityGreaterThanOne != orderHavingQuantityGreaterThanOne.get().getValue().getQuantity() - 1)
				{
					return String.format(
						"MISSING %s for %s for %d",
						orderHavingQuantityGreaterThanOne.get().getValue().getQuantity() - 1 - countOtherOrdersHavingQuantityGreaterThanOne,
						orderHavingQuantityGreaterThanOne.get().getValue().getContent(),
						orderHavingQuantityGreaterThanOne.get().getValue().getQuantity()
					);
				}
				
				return null;
			}
			
			return null;
		}
		
		return String.format("MISSING %d", maxOrders - orders.size());
	}
	
	public String present (final TablePresenter tablePresenter)
	{
		return orders.entrySet()
			.stream()
			.map(tablePresenter.getPresenter())
			.collect(Collectors.joining(tablePresenter.getSeparator()))
		;
	}
}
