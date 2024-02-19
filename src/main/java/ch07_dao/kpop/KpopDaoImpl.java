package ch07_dao.kpop;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class KpopDaoImpl implements KpopDao {
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			//JNDI를 이용하기 위한 객체 생성: 이름으로 객체 찾기 o 
			Context initContext = new InitialContext();
			
			// java:comp/env까지 initCtx의 lookup메서드-찾아서 넣음- 를 이용해서 "java:comp/env" 에 해당하는 객체(톰캣)를 찾아서 evnCtx에 삽입
			// + 부터 envCtx의 lookup메서드를 이용해서 "jdbc/world"에 해당하는 객체(server.xml에 등록한 이름 world)를 찾아서 ds에 삽입
			
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/world"); // "jdbc/world"는 context.xml에서 가져온 거
			
			//getConnection메서드를 이용해서 커넥션 풀로 부터 커넥션 객체를 얻어내어 conn변수에 저장
			conn = ds.getConnection();
			
			/* 위의 코드를 아래와 같이 줄여서 작성 가능하다. 
			 * Context context = new InitialContext(); 
			 * DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/world");             
			 * Connection con = ds.getConnection(); 
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public List<Kpop> getKpopList() {
		
		Connection conn = getConnection();
		List<Kpop> list = new ArrayList<Kpop>();
		// l.sid 누락 
		String sql = "SELECT g.*, s.title, s.lyrics FROM girl_group g"
				+ "  JOIN song s ON g.hit_song_id=s.sid"
				+ "  ORDER BY debut desc";
				
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			// 여기서 받는 거에 파라미터 1개 뺌 lyrics 받아야 할 걸 안받아서 lyrics자리에 번호 들어감
			// list.jsp 고치다 뺸 걸로 예상됨
			while (rs.next()) {
				Kpop kpop = new Kpop(rs.getInt(1), rs.getString(2), LocalDate.parse(rs.getString(3)), 
						rs.getInt(4), rs.getString(5), rs.getString(6));
				list.add(kpop);
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Artist getArtist(int aid) {
		Connection conn = getConnection();
		String sql = "select * from girl_group where gid=?";
		Artist artist = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aid);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				artist = new Artist(rs.getInt(1), rs.getString(2), 
						LocalDate.parse(rs.getString(3)), rs.getInt(4));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return artist;
	}

	@Override
	public Song getSong(int sid) {
		Connection conn = getConnection();
		String sql = "select * from song where sid=?";
		Song song = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				song = new Song(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return song;
	}

	@Override
	public void insertArtist(Artist artist) {
		Connection conn = getConnection();
		String sql = "insert into girl_group values(default, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, artist.getName());
			pstmt.setString(2, artist.getDebut().toString());
			pstmt.setInt(3, artist.getHitSongId());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertSong(Song song) {
		Connection conn = getConnection();
		String sql = "insert into song values(default, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, song.getTitle());
			pstmt.setString(2, song.getLyrics());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	// 다시 이해하기 
	public void updateArtist(Artist artist) {
		Connection conn = getConnection();
		String sql = "update girl_group set name=?, debut=?, hit_song_id=? where gid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, artist.getName());
			pstmt.setString(2, artist.getDebut().toString());
			pstmt.setInt(3, artist.getHitSongId());
			pstmt.setInt(4, artist.getAid());
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateSong(Song song) {
		Connection conn = getConnection();
		String sql = "update song set title=?, lyrics=? where sid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, song.getTitle());
			pstmt.setString(2, song.getLyrics());
			pstmt.setInt(3, song.getSid());
			
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	// 다시 이해하기 
	public void deleteArtist(int aid) {
		Connection conn = getConnection();
		String sql = "delete from girl_group where gid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aid);
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	@Override
	public void deleteSong(int sid) {
		Connection conn = getConnection();
		String sql = "delete from song where sid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
