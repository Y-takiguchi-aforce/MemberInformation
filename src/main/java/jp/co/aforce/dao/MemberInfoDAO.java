package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.MemberInfo;

public class MemberInfoDAO extends DAO {

	//会員情報の登録(SQL-MM01-I01)
	public int insert(MemberInfo info) throws Exception {
		Connection con = getConnection();
		//自動コミットモードのオフ
		con.setAutoCommit(false);
		PreparedStatement st = con.prepareStatement(
				"INSERT INTO member_information values(?,?,?,?,?,?,?,?,?,?)");
		st.setString(1, info.getMemberId());
		st.setString(2, info.getLastName());
		st.setString(3, info.getFirstName());
		st.setString(4, info.getSex());
		st.setInt(5, info.getBirthYear());
		st.setInt(6, info.getBirthMonth());
		st.setInt(7, info.getBirthDay());
		st.setString(8, info.getPhoneNumber());
		st.setString(9, info.getMailAddress());
		st.setString(10, info.getJob());
		st.executeUpdate();

		//会員情報の重複がないかチェックする機能(SQL-MM01-S01)
		st = con.prepareStatement(
				"SELECT * FROM member_information WHERE "
						+ "last_name=? "
						+ "and first_name=? "
						+ "and sex=? "
						+ "and birth_year=? "
						+ "and birth_month=? "
						+ "and birth_day=? "
						+ "and phone_number=? "
						+ "and mail_address=? "
						+ "and job=?");
		st.setString(1, info.getLastName());
		st.setString(2, info.getFirstName());
		st.setString(3, info.getSex());
		st.setInt(4, info.getBirthYear());
		st.setInt(5, info.getBirthMonth());
		st.setInt(6, info.getBirthDay());
		st.setString(7, info.getPhoneNumber());
		st.setString(8, info.getMailAddress());
		st.setString(9, info.getJob());
		ResultSet rs = st.executeQuery();
		int line = 0;
		while (rs.next()) {
			line++;
		}
		if (line == 1) {
			con.commit();
		} else {
			con.rollback();
		}
		//自動コミットモードのオン
		con.setAutoCommit(true);
		st.close();
		con.close();
		return line;
	}

	//会員情報の検索(SQL-MM02-S01)
	public List<MemberInfo> search(String member_id) throws Exception {
		List<MemberInfo> searchlist = new ArrayList<>();

		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement(
				"SELECT * FROM member_information WHERE member_id = ?");
		st.setString(1, member_id);
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			MemberInfo m = new MemberInfo();
			m.setMemberId(rs.getString("member_id"));
			m.setLastName(rs.getString("last_name"));
			m.setFirstName(rs.getString("first_name"));
			m.setSex(rs.getString("sex"));
			m.setBirthYear(rs.getInt("birth_year"));
			m.setBirthMonth(rs.getInt("birth_month"));
			m.setBirthDay(rs.getInt("birth_day"));
			m.setPhoneNumber(rs.getString("phone_number"));
			m.setMailAddress(rs.getString("mail_address"));
			m.setJob(rs.getString("job"));
			searchlist.add(m);
		}
		
		st.close();
		con.close();
		return searchlist;

	}

	//会員情報の更新(SQL-MM02-U01)
	public int update(MemberInfo info) throws Exception {
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
				"UPDATE member_information SET "
						+ "last_name=? "
						+ ", first_name=? "
						+ ", sex=? "
						+ ", birth_year=? "
						+ ", birth_month=? "
						+ ", birth_day=? "
						+ ", phone_number=? "
						+ ", mail_address=? "
						+ ", job=?"
						+ "WHERE member_id = ?");
		st.setString(1, info.getLastName());
		st.setString(2, info.getFirstName());
		st.setString(3, info.getSex());
		st.setInt(4, info.getBirthYear());
		st.setInt(5, info.getBirthMonth());
		st.setInt(6, info.getBirthDay());
		st.setString(7, info.getPhoneNumber());
		st.setString(8, info.getMailAddress());
		st.setString(9, info.getJob());
		st.setString(10, info.getMemberId());
		int updateline = st.executeUpdate();

		st.close();
		con.close();
		return updateline;

	}
	
	//会員情報の削除（SQL-MM03-D01）
	public int delete(MemberInfo info) throws Exception {
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
				"DELETE FROM member_information WHERE member_id = ?");
		st.setString(1, info.getMemberId());
		int deleteline = st.executeUpdate();

		st.close();
		con.close();
		return deleteline;

	}

}
