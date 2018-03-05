package samples.parsers;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import samples.entities.Client;
import samples.entities.OrderEntry;

public class DefaultOrderParser implements OrderParser
{

	@Override
	public Entry<Client, OrderEntry> parse(String order)
	{
		final String[] tokens = order.split(": ");
		
		final Client client = new Client();
		client.setName(tokens [0]);
		
		final Matcher matcher = Pattern.compile("^(\\S+) for (\\d+)$").matcher(tokens [1]);
		
		final OrderEntry orderEntry = new OrderEntry();
		
		if (matcher.find())
		{
			orderEntry.setContent(matcher.group(1));
			orderEntry.setQuantity(Integer.valueOf(matcher.group(2)));
		}
		else
		{
			orderEntry.setContent(tokens [1]);
			orderEntry.setQuantity(1);
		}
		
		return new SimpleEntry<> (client, orderEntry);
	}
	
}
