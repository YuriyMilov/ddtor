package gu.client;


import gu.client.view.ZgalaView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Gu implements EntryPoint {

	private final SrvAsync srv = GWT.create(Srv.class);
	LoginView login_view = new LoginView();
	private TextBox txtLogin = new TextBox();
	private PasswordTextBox txtPassword = new PasswordTextBox();
	final Label namePrompt = new Label("User ID: ");
	final Label passwordPrompt = new Label("Password: ");
	final Grid grid = new Grid(6, 4);
	final DecoratorPanel dp1 = new DecoratorPanel();
	// final VerticalPanel pan = new VerticalPanel();
	// final HorizontalPanel hp1 = new HorizontalPanel();
	// final Button but2 = new Button("GetID");
	final Button but_login = new Button(" Login ");
	final HTML loading = new HTML(
			"<table border=0 cellspacing=0 cellpadding=0 width=100%><tr width=100%><td align=left><font color=red>&nbsp;&nbsp;Checking accounts...&nbsp;&nbsp;</font></td></table>");
	final HTML er_mes1 = new HTML(
	"<table border=0 cellspacing=0 cellpadding=0 width=100%><tr width=100%><td align=left><font color=red>Session expired&nbsp;&nbsp;</font></td></table>");

	final HTML er_mes2 = new HTML(
	"<table border=0 cellspacing=0 cellpadding=0 width=100%><tr width=100%><td align=left><font color=red>Please try again&nbsp;&nbsp;</font></td></table>");

	public void onModuleLoad() {

		// ZgalaView view1 = new ZgalaView();
		// RootPanel.get().add(view1);

		srv.get("getid", "getid", new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new HTML(caught.toString()));
			}

			public void onSuccess(String rgetid) {
				// RootPanel.get().add(new HTML(rgetid));
				if (!rgetid.equals("qqq")) {
					 RootPanel.get().clear();
					// RootPanel.get().add(new HTML(rgetid));
					ZgalaView view1 = new ZgalaView();
					RootPanel.get().add(view1);

				} else {

					RootPanel.get().clear();
					prep_login();

					// pan.add(dp1);
					but_login.addClickHandler(clh_but_login);
					RootPanel.get().add(dp1, 5, 30);
					grid.setWidget(3, 2, er_mes1);
				}
			}
		});

	}

	ClickHandler clh_but_login = new ClickHandler() {

		public void onClick(ClickEvent event) {
			//dp1.removeFromParent();
			//qqq.removeFromParent();
			//loading.removeFromParent();
			
			RootPanel.get().clear();
			RootPanel.get().add(loading);

			String name = txtLogin.getText();
			String psw = txtPassword.getText();
			if ((name + psw).trim().length() == 0) {
				name = "qqq";
				psw = "qqq";
			}
			srv.set_id(name, psw, new AsyncCallback<String>() {

				public void onFailure(Throwable caught) {
					RootPanel.get().add(new HTML(caught.toString()));
				}

				public void onSuccess(String r) {

					if (!r.equals("qqq")) {
						// qqq.removeFromParent();
						// loading.removeFromParent();
						// hp1.setWidth("100%");
						// hp1.add(new HTML(r));
						// RootPanel.get().add(hp1);

						RootPanel.get().clear();
						ZgalaView view1 = new ZgalaView();
						RootPanel.get().add(view1);
					} else {
						// qqq.removeFromParent();
						// loading.removeFromParent();
						// pan.add(qqq);
						RootPanel.get().clear();
						grid.setWidget(3, 2, er_mes2);
						RootPanel.get().add(dp1, 5, 30);
						
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
