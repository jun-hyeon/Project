
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
	String a = "���б�";
	String b = "���� 1,2��"; 
	String c = "����";
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
		setTitle("2018�⵵ �������б� ���� ����");			
		
		JPanel total = new JPanel();
		JPanel msg = new JPanel();
		JPanel combo = new JPanel();
		JPanel content = new JPanel();
		JButton ok = new JButton("Ȯ��");		
		JLabel infor = new JLabel("���Ͻô� ������ �����ϼ���.");
		
		String[] schoolname = {"���б�","���Ѵ��б�","��õ���б�","�����������б�","���ϰ����������б�","����̷����б�"};	
		String[] lastyear = {"���� 1,2","����1��","����2��"};		
		String[] score = {"����","����","��õ","���"};
		
			
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
		//Ȯ�ι�ư ������ �� �̺�Ʈ
		
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					if(a!="���б�"&&b!="���� 1,2��"&&c=="����") {
						  String sql = "select �а� ,"+ b +", �����ο� from "+ a;
						  PreparedStatement pat = connection.prepareStatement(sql);
						  ResultSet rs = pat.executeQuery();
						  table.setModel(DbUtils.resultSetToTableModel(rs));
						  
					} else  {
					
						 String sql = "select "+ c +" from ����";
						   PreparedStatement pat = connection.prepareStatement(sql);
							ResultSet rs = pat.executeQuery();
						  table.setModel(DbUtils.resultSetToTableModel(rs));
					}
					
				}catch(Exception e) 
				{ e.printStackTrace(); }
		      }		
		});		
			//�޺��ڽ��� ���� �������� �� �����ϴ� �̺�Ʈ
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
