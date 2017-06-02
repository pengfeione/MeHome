package com.mehome.pay.iface;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.mehome.exceptions.BaseException;
import com.mehome.exceptions.DatabaseException;
import com.mysql.jdbc.Connection;

public interface IPaymentServiceConnector {
	public Connection getConnection() throws DatabaseException;
	public <T> T getJndi(String jndiName, T defVal);
	public String createPayment(Connection conn, String settlementId, String orderId, String productName, String productDetail, String productExt, BigDecimal price) throws SQLException, BaseException;
	public <PaidInfo extends BasePaidInfo<? extends NotifyInfo, ? extends PayChannel>,
		NotifyInfo extends INotifyInfo<? extends PayChannel>,
		PayChannel extends IPayChannel>
		void paidSuccess(Connection conn, PaidInfo paidInfo) throws SQLException;

}
