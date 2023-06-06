package jp.co.aforce.delete;

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
@WebServlet("/view/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			//削除したい会員情報の会員番号を入力
			String member_id = request.getParameter("member_id");
			
			MemberInfo m = new MemberInfo();
			m.setMemberId(member_id);
			
			MemberInfoDAO dao = new MemberInfoDAO();
			int deleteline = dao.delete(m);
			
			if(deleteline > 0) {
				request.getRequestDispatcher("delete_success.jsp").forward(request, response);
			}else {
				out.println("情報削除に失敗しました。");
			}
			
		}catch(Exception e){
			e.printStackTrace(out);
		}
		Page.footer(out);
	}

}
