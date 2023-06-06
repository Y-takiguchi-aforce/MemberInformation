package jp.co.aforce.update;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.MemberInfo;
import jp.co.aforce.dao.MemberInfoDAO;
import jp.co.aforce.tool.Page;

/**
 * Servlet implementation class Update
 */
@WebServlet("/view/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Update() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Page.header(out);
		try {
			
			//更新情報の登録
			String member_id = request.getParameter("member_id");
			String lastName = request.getParameter("last_name");
			String firstName = request.getParameter("first_name");
			String sex = request.getParameter("sex");
			int birthYear = Integer.parseInt(request.getParameter("birth_year"));
			int birthMonth = Integer.parseInt(request.getParameter("birth_month"));
			int birthDay = Integer.parseInt(request.getParameter("birth_day"));
			String phoneNum = request.getParameter("phone_number");
			String mailAdd = request.getParameter("mail_address");
			String job = request.getParameter("job");
			
			MemberInfo m = new MemberInfo();
			m.setMemberId(member_id);
			m.setLastName(lastName);
			m.setFirstName(firstName);
			m.setSex(sex);
			m.setBirthYear(birthYear);
			m.setBirthMonth(birthMonth);
			m.setBirthDay(birthDay);
			m.setPhoneNumber(phoneNum);
			m.setMailAddress(mailAdd);
			m.setJob(job);
			
			MemberInfoDAO dao = new MemberInfoDAO();
			int updateline = dao.update(m);
			
			if(updateline > 0) {
				request.getRequestDispatcher("update_success.jsp").forward(request, response);
			}else {
				out.println("情報更新に失敗しました。");
			}
			
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}

	
}