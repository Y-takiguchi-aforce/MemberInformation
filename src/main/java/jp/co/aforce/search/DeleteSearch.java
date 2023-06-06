package jp.co.aforce.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.MemberInfo;
import jp.co.aforce.dao.MemberInfoDAO;
import jp.co.aforce.tool.Page;

/**
 * Servlet implementation class Search
 */
@WebServlet("/view/delete_search")
public class DeleteSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteSearch() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Page.header(out);

		try {
			//会員番号で削除したい登録情報の検索
			String member_id = request.getParameter("member_id");
			MemberInfoDAO dao = new MemberInfoDAO();
			List<MemberInfo> searchlist = dao.search(member_id);
			out.println("会員番号が見つかりました。<br>");
			for(MemberInfo m : searchlist) {
				out.println("会員番号：" + m.getMemberId() + "<br>");
				out.println("姓：" + m.getLastName() + "<br>");
				out.println("名：" + m.getFirstName() + "<br>");
				out.println("性別：" + m.getSex() + "<br>");
				out.println("生年月日：" + m.getBirthYear() + "年");
				out.println(m.getBirthMonth() + "月");
				out.println(m.getBirthDay() + "日<br>");
				out.println("電話番号：" + m.getPhoneNumber() + "<br>");
				out.println("メールアドレス：" + m.getMailAddress() + "<br>");
				out.println("職業：" + m.getJob());
			}
			
		request.getRequestDispatcher("delete.jsp").include(request, response);
		
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}

}
