package samples.parsers;

import java.util.Map;

import samples.entities.Client;
import samples.entities.OrderEntry;

public interface OrderParser
{
	Map.Entry<Client, OrderEntry> parse (String order);
}
