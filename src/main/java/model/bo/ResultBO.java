package model.bo;

import java.util.ArrayList;

import model.bean.ConvertHistory;
import model.dao.ResultDAO;

public class ResultBO {
	public ArrayList<ConvertHistory> getConvertHistoryByUser(String username){
		ResultDAO dao = new ResultDAO();
		return dao.getConvertHistoryByUser(username);
	}
}
