package jp.co.aforce.regist;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.MemberInfo;
import jp.co.aforce.dao.MemberInfoDAO;
import jp.co.aforce.tool.Page;

/**
 * Servlet implementation class Regist
 */
@WebServlet("/view/regist")
public class Regist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Regist() {
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
			//現在日付を取得する
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
			String formattedDate = format.format(calendar.getTime());
			//会員番号の作成
			String idMake = "A" + formattedDate;

			//formから会員情報登録
			String memberId = idMake;
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
			m.setMemberId(memberId);
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
			int insertline = dao.insert(m);

			if (insertline == 1) {
				request.getRequestDispatcher("regist_success.jsp").forward(request, response);
			}else {
				out.println("会員情報の重複が考えられるため、登録できませんでした。");
			}

		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}
