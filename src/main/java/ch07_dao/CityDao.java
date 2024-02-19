package ch07_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare.Builder;



/*
 * Web에서 DB를 엑세스하는 방법: DBCP(DataBase Connection Pool)
 * 
 * 1. webapp/WEB-INF/lib 에 database library (.jar) 추가
 * 2. Tomcat server의 context.xml 수정
 * 
 */
public class CityDao {
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			//JNDI를 이용하기 위한 객체 생성: 이름으로 객체 찾기 o 
			Context initContext = new InitialContext();
			
			// java:comp/env까지 initCtx의 lookup메서드-찾아서 넣음- 를 이용해서 "java:comp/env" 에 해당하는 객체(톰캣)를 찾아서 evnCtx에 삽입
			// + 부터 envCtx의 lookup메서드를 이용해서 "jdbc/world"에 해당하는 객체(server.xml에 등록한 이름 world)를 찾아서 ds에 삽입
			
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/bbs"); // "jdbc/world"는 context.xml에서 가져온 거
			
			//getConnection메서드를 이용해서 커넥션 풀로 부터 커넥션 객체를 얻어내어 conn변수에 저장
			conn = ds.getConnection();
			
			/* 위의 코드를 아래와 같이 줄여서 작성 가능하다. 
			 * Context context = new InitialContext(); 
			 * DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");             
			 * Connection con = dataSource.getConnection(); 
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public City getCity(int id) {
		Connection conn = getConnection();
		String sql ="select * from kcity where id=?";
		City city = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				city = new City(rs.getInt(1), rs.getString(2),rs.getString(3), 
						rs.getString(4), rs.getInt(5));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}
	
	public List<City> getCityList(String district, int num, int offset){
		Connection conn = getConnection();
		String sql ="select * from kcity where district=? limit ? offset ?";
		List<City> list = new ArrayList<City>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, district);
			pstmt.setInt(2, num);
			pstmt.setInt(3, offset);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				City city = new City(rs.getInt(1), rs.getString(2),rs.getString(3), 
						rs.getString(4), rs.getInt(5));
				list.add(city);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void insertCity(City city) {
		Connection conn = getConnection();
		String sql ="insert into kcity values (default, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, city.getName());
			pstmt.setString(2, city.getCountryCode());
			pstmt.setString(3, city.getDistrict());
			pstmt.setInt(4, city.getPopulation());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteCity(int id) {
		Connection conn = getConnection();
		String sql ="delete from kcity where id=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void updateCity(City city) {
		Connection conn = getConnection();
		String sql ="update kcity set name=?, countryCode=?, district=?, population=? where id=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, city.getName());
			pstmt.setString(2, city.getCountryCode());
			pstmt.setString(3, city.getDistrict());
			pstmt.setInt(4, city.getPopulation());
			pstmt.setInt(5, city.getId());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
