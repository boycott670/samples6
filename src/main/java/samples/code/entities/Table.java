package samples.code.entities;

import java.util.ArrayList;
import java.util.List;

import samples.code.parsers.OrderEntryParser;
import samples.code.presenters.DefaultOrderEntriesPresenter;
import samples.code.presenters.OrderEntriesPresenter;

public class Table {

	private final OrderEntriesPresenter presenter = new DefaultOrderEntriesPresenter();
	private final List<OrderEntry> entries = new ArrayList<>();
	private final int size;
	
	
	
	public Table(final int size)
	{
		this.size = size;
	}

	public void addOrderEntry (final String orderEntry, final String separator, final OrderEntryParser parser)
	{
		final OrderEntry entry = parser.parse(orderEntry, separator);
		
		if (entries.contains(entry))
		{
			entries.remove(entry);
		}
				
		if ("Same".equals(entry.getRecipe()))
		{
			if (!entries.isEmpty())
			{
				entries.add(new OrderEntry(entry.getClient(), entries.get(entries.size() - 1).getRecipe()));
			}
			else
			{
				throw new IllegalStateException();
			}
		}
		else
		{
			entries.add(entry);
		}
		
	}
	
	public String present ()
	{
		if(size != entries.size())
		{
			return presenter.errorMessage(size - entries.size());
		}
		return presenter.present(entries);
	}
	
}
