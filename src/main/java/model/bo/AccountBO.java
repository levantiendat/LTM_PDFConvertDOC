package model.bo;

import model.bean.Account;
import model.dao.AccountDAO;

public class AccountBO {
	public Boolean addNewAccount(Account account) {
		AccountDAO dao = new AccountDAO();
		return dao.addNewAccount(account);
	}
	public Account getAccount(String username, String password) {
		AccountDAO dao = new AccountDAO();
		return dao.getAccount(username, password);
	}
}
