package g2.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class G2 implements EntryPoint {
	
	RequestBuilder qq1 = new RequestBuilder(RequestBuilder.GET,
			"qq1");
	
		public void onModuleLoad() {
			try {
				qq1.sendRequest(null, new RequestCallback() 
				{
					@Override
					public void onResponseReceived(Request request,
							Response response) {
						RootPanel.get().add(new HTML(response.getText().toString()));
					}
					@Override
					public void onError(Request request, Throwable exception) {
						RootPanel.get().add(new HTML(exception.toString()));
						}
				});
			} catch (Exception e) {
				RootPanel.get().add(new HTML(e.toString()));
			}
		}
}
