package samples.presenters;

import java.util.Map.Entry;
import java.util.function.Function;

import samples.entities.Client;
import samples.entities.OrderEntry;

public interface TablePresenter
{
	Function<? super Entry<Client, OrderEntry>, ? extends String> getPresenter ();
	String getSeparator ();
}
