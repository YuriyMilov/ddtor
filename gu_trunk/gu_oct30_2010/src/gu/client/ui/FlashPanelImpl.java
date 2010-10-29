package gu.client.ui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class FlashPanelImpl {

	HTML flashCode = new HTML();
	public Widget getWidget()
	{
		return flashCode;
	}
	public void setURL( String url, String width, String height, String flashVars )
	{
		flashCode.setHTML( "<embed"+
					" height='"+height+"' width='"+width+"' "+
					" src='"+url+"'"+
					" flashVars='"+flashVars+"'"+
					" quality='high'"+ 
					" wmode='transparent'"+
					" align='absmiddle'"+
					" allowScriptAccess='sameDomain'"+
					" type='application/x-shockwave-flash'"+
					" pluginspage='http://www.macromedia.com/go/getflashplayer' />" );
	}	
}
