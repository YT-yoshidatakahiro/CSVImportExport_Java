import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class DataReadWrite{


	public static void main(String args[]) {


		JFrame frame = new JFrame();
		frame.setLayout(null);
		frame.setBounds(10, 10, 570, 700);
		frame.setTitle("課題2_データ読書");
		frame.setVisible(true);


		String[][] tabdata = new String[120][4];

		String[] title = {"col1","col2","col3","col4"};

		//ボタン_読み書きボタン
		JButton btn_Read = new JButton("読込");
		btn_Read.setBounds(50,50,100,50);
		btn_Read.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 30));
		frame.add(btn_Read);
		btn_Read.setVisible(true);
		btn_Read.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					File Datfile =new File("C:\\EclipsePhoton64\\workspace\\kadai2_java\\kadai2dat.csv");
					FileReader datread= new FileReader(Datfile);
					BufferedReader  Datbufread = new BufferedReader(datread);

					int j = 0;
					while(Datbufread != null) {

						String test[] = Datbufread.readLine().split(",");
						for(int i =0; i<test.length;i++) {
							System.out.print(test[i]);
							tabdata[j][i] = test[i];
						}
						System.out.println("");
						j++;

						if(j==100) {
							break;
						}
					}
					Datbufread.close();

					JTable dattable = new JTable(tabdata, title);
					dattable.setBounds(50,100,450,500);
					frame.add(dattable);
					dattable.setVisible(true);
				    Vector<String> headers = new Vector<String>();
				    JScrollPane  scroll_table = new JScrollPane(dattable);            // add table to scroll panel
					scroll_table.setBounds(50,100,450,450);
				    scroll_table.setVisible(true);
				    frame.add(scroll_table);

				}
				catch(IOException e1) {
					System.out.println("");
				}
			}
		});

		//ボタン_書き込みボタン
		JButton btn_Write = new JButton("書込");
		btn_Write.setBounds(400,550,100,50);
		btn_Write.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 30));
		frame.add(btn_Write);
		btn_Write.setVisible(true);
		btn_Write.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        //データ掃き出し
		        try {
		        	String path_w ="C:\\EclipsePhoton64\\workspace\\kadai2_java\\sample.csv";
		        	String[] db = new String[99];
		            // 出力ファイルの作成
		        	if(!(new File(path_w).exists())) {
		        		(new File(path_w)).createNewFile();
		        	}

		        	FileWriter f = new FileWriter("C:\\EclipsePhoton64\\workspace\\kadai2_java\\sample.csv", false);
		            PrintWriter p = new PrintWriter(new BufferedWriter(f));

		            // ヘッダーを指定する
		/*
		            p.print("dba");
		            p.print(",");
		            p.print("名前");
		            p.println();
		*/
		            // 内容をセットする
		            String kekka= "";
		            for(int i = 0; i < 100; i++){
		                for(int j = 0; j < 4; j++){
		                	kekka += tabdata[i][j];
		                }
	                    p.print((i+1)+kekka);
		                p.println();
		                kekka = "";

		                if(i==99) {
		                	break;
		                }
		            }
		            p.close();
		            System.out.println("ファイル出力完了！");
		        }
		        catch (IOException ex) {
		            ex.printStackTrace();
		        }
			}
		});



	}
}