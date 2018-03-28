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
		int indice = -1;
		
		if ((indice = entries.indexOf(entry)) != -1)
		{
			entries.remove(entry);
		}
				
		if ("Same".equals(entry.getRecipe()))
		{
			if (!entries.isEmpty())
			{
				entries.add(new OrderEntry(entry.getClient(), entries.get(entries.size() - 1).getRecipe(), entry.getQuantity()));
			}
			else
			{
				throw new IllegalStateException();
			}
		}
		else
		{
			 if(indice == -1)
			 { 
				 entries.add(entry);
			 }else 
				 entries.add(indice, entry);
			 
		}
		
	}
	
	public String present ()
	{
		if(size != entries.size())
		{
			return presenter.errorMessage(size - entries.size());
		}
		
		for (int i = 0 ; i < entries.size() ; i++)
		{
			if (entries.get(i).getQuantity() > 1)
			{
				int counter = 1;
				
				for (int j = 0 ; j < entries.size() ; j++)
				{
					if (i != j &&
						entries.get(j).getRecipe().equals(entries.get(i).getRecipe()) &&
						entries.get(j).getQuantity() == entries.get(i).getQuantity())
					{
						counter ++;
					}
				}
				
				if (counter != entries.get(i).getQuantity())
				{
					return presenter.errorMessage(entries.get(i).getQuantity() - counter, entries.get(i).getRecipe(), entries.get(i).getQuantity());
				}
			}
		}
		
		return presenter.present(entries);
	}
	
}
