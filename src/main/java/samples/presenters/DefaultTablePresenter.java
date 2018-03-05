package samples.presenters;

import java.util.Map.Entry;
import java.util.function.Function;

import samples.entities.Client;
import samples.entities.OrderEntry;

public class DefaultTablePresenter implements TablePresenter
{

	@Override
	public Function<? super Entry<Client, OrderEntry>, ? extends String> getPresenter()
	{
		return order -> order.getValue().getQuantity() == 1 ?
			order.getValue().getContent() :
			String.format("%s for %d", order.getValue().getContent(), order.getValue().getQuantity())
		;
	}

	@Override
	public String getSeparator()
	{
		return ", ";
	}
	
}
