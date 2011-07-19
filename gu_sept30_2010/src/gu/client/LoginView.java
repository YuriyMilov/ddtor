package gu.client;

import gu.client.view.PanView;
import gu.client.view.UfosView;
import gu.client.view.ZgalaView;
import gu.client.Srv;
import gu.client.SrvAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginView extends Composite {
	private TextBox txtLogin = new TextBox();
	private PasswordTextBox txtPassword = new PasswordTextBox();
	final Label namePrompt = new Label("User ID: ");
	final Label passwordPrompt = new Label("Password: ");
	private final SrvAsync srv = GWT.create(Srv.class);
	final Grid grid = new Grid(6, 4);
	final DecoratorPanel dp1 = new DecoratorPanel();
	final VerticalPanel pan = new VerticalPanel();
	//final HorizontalPanel hp1 = new HorizontalPanel();
	// final Button but2 = new Button("GetID");
	final Button but_login = new Button(" Login ");
	final HTML loading = new HTML(
			"<table border=0 cellspacing=0 cellpadding=0 width=100%><tr width=100%><td align=left><font color=red>&nbsp;&nbsp;Checking accounts...&nbsp;&nbsp;</font></td></table>");
	final HTML qqq = new HTML(
			"<table border=0 cellspacing=0 cellpadding=0 width=100%><tr width=100%><td align=left><font color=red>&nbsp;&nbsp;No such user, please try again&nbsp;&nbsp;</font></td></table>");

	public LoginView() {
		prep_login();
		pan.add(dp1);
		but_login.addClickHandler(clh_but_login);
		// but2.addClickHandler(clh_b2);
		initWidget(pan);
	}

	ClickHandler clh_b2 = new ClickHandler() {

		public void onClick(ClickEvent event) {

			srv.get("getid", "getid", new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
				}

				public void onSuccess(String rgetid) {
					pan.add(new HTML(rgetid));
				}
			});
		}
	};

	ClickHandler clh_but_login = new ClickHandler() {

		public void onClick(ClickEvent event) {
			dp1.removeFromParent();
			qqq.removeFromParent();
			loading.removeFromParent();

			RootPanel.get().add(loading);

			String name = txtLogin.getText();
			String psw = txtPassword.getText();
			//if (name.equals(""))
			//	name = "guest";
			//if (psw.equals(""))
			//	psw = "guest";
			srv.set_id(name, psw, new AsyncCallback<String>() {

				public void onFailure(Throwable caught) {
					RootPanel.get().add(new HTML(caught.toString()));
				}

				public void onSuccess(String r) {
					if (!r.equals("qqq")) {
						qqq.removeFromParent();
						loading.removeFromParent();
						//hp1.setWidth("100%");
						//hp1.add(new HTML(r));
						//RootPanel.get().add(hp1);
						ZgalaView view1 = new ZgalaView();
						pan.add(view1);
					} else {
						qqq.removeFromParent();
						loading.removeFromParent();
						// pan.add(qqq);
						grid.setWidget(3, 2, qqq);
						pan.add(dp1);
					}
				}

			});
		}
	};

	void prep_login() {
		grid.setWidget(0, 0, new HTML("&nbsp;"));
		grid.setWidget(1, 1, namePrompt);
		grid.setWidget(1, 2, txtLogin);
		grid.setWidget(2, 1, passwordPrompt);
		grid.setWidget(2, 2, txtPassword);
		but_login.setWidth("88px");
		grid.setWidget(4, 2, but_login);
		grid.setWidget(5, 3, new HTML("&nbsp;&nbsp;&nbsp;"));
		dp1.add(grid);
	}
}