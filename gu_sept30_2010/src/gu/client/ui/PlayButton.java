package gu.client.ui;

import com.google.gwt.user.client.ui.HTML;

public class PlayButton extends HTML
{
	public PlayButton(){}
	public PlayButton( String url )
	{
		setUrl( url );
	}
	
	public void setUrl( String url )
	{
		setHTML("<object height='24' width='100'"+
				" classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000'"+
				" codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0'"+
				" align='absmiddle'"+
				" VIEWASTEXT>"+
					"<param name='wmode' value='transparent' />"+
					"<param name='allowScriptAccess' value='sameDomain' />"+
					"<param name='flashVars' value='theLink="+url+"' />"+
					"<param name='movie' value='http://del.icio.us/static/swf/playtagger.swf' />"+ 
					"<param name='quality' value='high' />"+ 
					"<embed"+
					" height='24' width='100' "+
					" src='http://del.icio.us/static/swf/playtagger.swf'"+
					" flashVars='theLink="+url+"'"+
					" quality='high'"+ 
					" wmode='transparent'"+
					" align='absmiddle'"+
					" allowScriptAccess='sameDomain'"+
					" type='application/x-shockwave-flash'"+
					" pluginspage='http://www.macromedia.com/go/getflashplayer' />"+ 
				"</object>");
	}

}
