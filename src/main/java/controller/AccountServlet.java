package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Account;
import model.bo.AccountBO;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String isLogin = request.getParameter("signin");
		String isRegister = request.getParameter("signup");
		String isLogout = request.getParameter("logout");
		if("1".equals(isLogin)) {
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			Account account = new Account();
			AccountBO bo = new AccountBO();
			account = bo.getAccount(username, password);
			if(account!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("username", account.getUsername());
				String destination = "/inputFile.jsp";
				RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
				rd.forward(request, response);
			} else {
				request.setAttribute("isFailed", "Đăng nhập không thành công");
				String destination = "/Login.jsp";
				RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
				rd.forward(request, response);
			}
		} else if("0".equals(isRegister)) {
			String destination = "/Register.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		} else if("1".equals(isRegister)) {
			String destination = null;
			try {
				
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String email = request.getParameter("email");
				
				Account account = new Account(username, password, email);
				AccountBO bo = new AccountBO();
				Boolean kq = bo.addNewAccount(account);
				if(kq) {
					destination = "/Login.jsp";
					RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
					rd.forward(request, response);
				} else {
					destination = "/Register.jsp";
					RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
					rd.forward(request, response);
				}
			} catch(Exception e) {
				destination = "/Register.jsp";
				RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
				rd.forward(request, response);
			}
		} else if("1".equals(isLogout)) {
			HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate();
	        }
	        response.sendRedirect("Login.jsp");
		}else {
			String destination = "/Login.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		}
	}

}
