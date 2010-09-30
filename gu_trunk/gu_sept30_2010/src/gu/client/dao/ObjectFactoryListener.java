package gu.client.dao;

public interface ObjectFactoryListener {
	void onRefresh();
	void onError(String error);
	void onLoadingStart();
	void onLoadingFinish();
	
}
