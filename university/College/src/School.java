
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

class SchoolInfor extends JFrame {
	Connection connection = null;
	String a = "대학교";
	String b = "수시 1,2차"; 
	String c = "지역";
	JComboBox combo1;
	JComboBox combo2;
	JComboBox combo3;
	JButton ok;
	JTable table;
	TableModel tm;
	
	
	SchoolInfor() {
		
		connection = DataBase.dataConnction();
		setSize(900,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("2018년도 전문대학교 수시 모집");			
		
		JPanel total = new JPanel();
		JPanel msg = new JPanel();
		JPanel combo = new JPanel();
		JPanel content = new JPanel();
		JButton ok = new JButton("확인");		
		JLabel infor = new JLabel("원하시는 정보를 선택하세요.");
		
		String[] schoolname = {"대학교","유한대학교","부천대학교","명지전문대학교","인하공업전문대학교","동양미래대학교"};	
		String[] lastyear = {"수시 1,2","수시1차","수시2차"};		
		String[] score = {"지역","서울","인천","경기"};
		
			
		JComboBox combo1 = new JComboBox(schoolname);
		JComboBox combo2 = new JComboBox(lastyear);
		JComboBox combo3 = new JComboBox(score);			
		
		combo1.setPreferredSize(new Dimension(250,30));
		combo2.setPreferredSize(new Dimension(200,30));
		combo3.setPreferredSize(new Dimension(200,30));
		content.setPreferredSize(new Dimension(770,370));
		
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(750,350));
		content.add(scrollPane);
		scrollPane.setViewportView(table);	
		//확인버튼 눌렀을 때 이벤트
		
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					if(a!="대학교"&&b!="수시 1,2차"&&c=="지역") {
						  String sql = "select 학과 ,"+ b +", 모집인원 from "+ a;
						  PreparedStatement pat = connection.prepareStatement(sql);
						  ResultSet rs = pat.executeQuery();
						  table.setModel(DbUtils.resultSetToTableModel(rs));
						  
					} else  {
					
						 String sql = "select "+ c +" from 지역";
						   PreparedStatement pat = connection.prepareStatement(sql);
							ResultSet rs = pat.executeQuery();
						  table.setModel(DbUtils.resultSetToTableModel(rs));
					}
					
				}catch(Exception e) 
				{ e.printStackTrace(); }
		      }		
		});		
			//콤보박스의 값을 선택했을 때 저장하는 이벤트
			combo1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					 a = combo1.getSelectedItem().toString();
					
				}
			});
			
			combo2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					b = combo2.getSelectedItem().toString();
					
				}
			});
			
			combo3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					c = combo3.getSelectedItem().toString();
					
				}
			});
			
		msg.setBackground(Color.WHITE);
		combo.setBackground(Color.WHITE);
		content.setBackground(Color.pink);
		total.setBackground(Color.pink);
		
		msg.add(infor);
		combo.add(combo1);
		combo.add(combo2);
		combo.add(combo3);
		combo.add(ok);
				
		total.add(msg);
		total.add(combo);
		total.add(content);
		
		add(total);	
					
		setVisible(true);
	}	
}


public class School {
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		new SchoolInfor();
	}
}
