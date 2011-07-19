package gu.client.ui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class FlashPanelImplIE6 extends FlashPanelImpl
{
	public void setURL( String url, String width, String height, String flashVars )
	{
		flashCode.setHTML( "<object height='"+height+"' width='"+width+"'"+
				" classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000'"+
				" codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0'"+
				" align='absmiddle'"+
				" VIEWASTEXT>"+
					"<param name='wmode' value='transparent' />"+
					"<param name='allowScriptAccess' value='sameDomain' />"+
					"<param name='flashVars' value='"+flashVars+"' />"+
					"<param name='movie' value='"+url+"' />"+ 
					"<param name='quality' value='high' />"+ 
				"</object>" );
	}
}
