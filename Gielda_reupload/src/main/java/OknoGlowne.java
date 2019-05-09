package main.java;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class OknoGlowne extends JFrame implements Serializable {

	private JPanel contentPane;
	private JTextField txtGie�da;
	private JTextField txtRynekWalut;
	private JTextField txtRynekSurowcw;
	private JTextField txtCenaWykupu;
	
	private Spolka wybranaSpolka;


	
	/**
	 * Create the frame.
	 */
	public OknoGlowne() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(25, 25, 1072, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDodaj = new JButton("Dodaj  obiekt");
		btnDodaj.setBounds(514, 10, 120, 30);
		contentPane.add(btnDodaj);
		
		JLabel lblGieda = new JLabel("Gie�da:");
		lblGieda.setBounds(10, 10, 90, 13);
		contentPane.add(lblGieda);
		
		txtGie�da = new JTextField();
		txtGie�da.setBounds(10, 30, 150, 19);
		contentPane.add(txtGie�da);
		txtGie�da.setColumns(10);
		txtGie�da.setEditable(false);
		if(!Swiat.listaWsyztskichGield.isEmpty()) {
			txtGie�da.setText(Swiat.listaWsyztskichGield.get(0).getNazwa());
		}else {txtGie�da.setText("Brak gie�dy!");}
		
		JLabel lblAkcje = new JLabel("Akcje:");
		lblAkcje.setBounds(10, 54, 50, 13);
		contentPane.add(lblAkcje);
		
		JTextArea textAreaInfoOSpolce = new JTextArea();
		textAreaInfoOSpolce.setBounds(10, 490, 814, 48);
		contentPane.add(textAreaInfoOSpolce);
		textAreaInfoOSpolce.setLineWrap(true);
		textAreaInfoOSpolce.setWrapStyleWord(true);
		
		JLabel lblInfoOSpolce = new JLabel("Informacje o sp�ce:");
		lblInfoOSpolce.setBounds(10, 472, 150, 13);
		contentPane.add(lblInfoOSpolce);
		
		JList<Spolka> listAkcje = new JList<>();
		listAkcje.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listAkcje.setBounds(10, 74, 150, 350);
		contentPane.add(listAkcje);
		DefaultListModel<Spolka> modelListAkcje = new DefaultListModel<>();
		if(!Swiat.listaWsyztskichGield.isEmpty()) {//je�eli istnieje gie�da, to wy�wietla wsyztskie akcje
			for (Spolka s : Gielda.listaWsyztskichSpolek) {
				modelListAkcje.addElement(s);
			}
		}
		listAkcje.setModel(modelListAkcje);
		
		JScrollPane scrollPaneAkcje = new JScrollPane(listAkcje);
		scrollPaneAkcje.setBounds(10, 74, 150, 350);
		contentPane.add(scrollPaneAkcje);
		
		txtCenaWykupu = new JTextField();
		txtCenaWykupu.setText("Cena wykupu");
		txtCenaWykupu.setBounds(10, 434, 120, 19);
		contentPane.add(txtCenaWykupu);
		txtCenaWykupu.setColumns(10);
		txtCenaWykupu.setEditable(false);
		
		JButton btnWykup = new JButton("Wykup");
		btnWykup.setBounds(140, 434, 85, 19);
		contentPane.add(btnWykup);
		btnWykup.setEnabled(false);
				
		listAkcje.addMouseListener(new MouseAdapter() {//wy�wietlanie info o Sp�ce po klikni�ciu na akcj� umo�liwia wykup akcji przez sp�k�
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JList source = (JList)arg0.getSource();
				wybranaSpolka = (Spolka)source.getSelectedValue();
				Akcja a = wybranaSpolka.getAkcja();
				textAreaInfoOSpolce.setText("Sp�ka: "+wybranaSpolka.getNazwa()+"   Liczba akcji: "+wybranaSpolka.getLiczbaAkcji()+"\nData pierwszej wyceny akcji: "+
				a.getDataPierwszejWyceny()+"   Kurs otwarcia: "+a.getKursOtwarcia()+"   Aktualny kurs: "+a.getAktualnyKurs()+"   Minimalny kurs: "+
				a.getMinKurs()+"   Maksymalny kurs: "+a.getMaxKurs()+"\nZysk: "+wybranaSpolka.getZysk()+"   Przych�d: "+wybranaSpolka.getPrzych�d()+"   Kapita� w�asny: "+
				wybranaSpolka.getKapitalWlasny()+"   Kapita� zak�adowy: "+wybranaSpolka.getKapitalZalkadowy()+"   Wolumen: "+wybranaSpolka.getWolumen()+
				"   Obroty: "+wybranaSpolka.getObroty());
				
				//wykup akcji przez sp�k�
				txtCenaWykupu.setText("");
				txtCenaWykupu.setEditable(true);
				btnWykup.setEnabled(true);
			}
		});
								
		JLabel lblRynekWalut = new JLabel("Rynek walut");
		lblRynekWalut.setBounds(170, 10, 90, 13);
		contentPane.add(lblRynekWalut);
				
		txtRynekWalut = new JTextField();
		txtRynekWalut.setBounds(170, 30, 150, 19);
		contentPane.add(txtRynekWalut);
		txtRynekWalut.setColumns(10);
		txtRynekWalut.setEditable(false);
		if(!Swiat.listaWsyztskichRynkowWalut.isEmpty()) {
			txtRynekWalut.setText(Swiat.listaWsyztskichRynkowWalut.get(0).getNazwa());
		}else {txtRynekWalut.setText("Brak rynku walut!!");}
		
		JLabel lblWaluty = new JLabel("Waluty:");
		lblWaluty.setBounds(170, 54, 50, 13);
		contentPane.add(lblWaluty);
		
		JLabel lblInfoOWalucie = new JLabel("Informacje o walucie:");
		lblInfoOWalucie.setBounds(10, 548, 150, 13);
		contentPane.add(lblInfoOWalucie);
		
		JTextArea textAreaInfoOWalucie = new JTextArea();
		textAreaInfoOWalucie.setBounds(10, 566, 814, 32);
		contentPane.add(textAreaInfoOWalucie);
		
		JScrollPane scrollPaneWaluty = new JScrollPane();
		scrollPaneWaluty.setBounds(170, 74, 150, 350);
		contentPane.add(scrollPaneWaluty);
		
		JList<Waluta> listWaluty = new JList<>();
		listWaluty.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneWaluty.setViewportView(listWaluty);	
		DefaultListModel<Waluta> modelListWaluty = new DefaultListModel<>();
		if(!Swiat.listaWsyztskichRynkowWalut.isEmpty()) {//je�eli istnieje rynek, to wy�wietla wsyztskie waluty
			for (Waluta w : RynekWalut.listaWszytskichWalut) {
				modelListWaluty.addElement(w);
			}
		}
		listWaluty.setModel(modelListWaluty);
		
		listWaluty.addMouseListener(new MouseAdapter() {//wy�wietla info o walucie po klikni�ciu na walut� z listy
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JList source = (JList)arg0.getSource();
				Waluta w = (Waluta)source.getSelectedValue();
				String kraje = "";
				for (String s : w.getListaKrajow()) {
					kraje += s+", ";
				}
				textAreaInfoOWalucie.setText("Waluta: "+w.getNazwa()+"   Cena kupna: "+w.getCenaKupna()+"   Cena sprzeda�y: "+w.getCenaSprzedazy()+
						"\nLista ktaj�w: "+kraje);			
			}
		});
						
		JLabel lblRynekSurowcw = new JLabel("Rynek surowc�w");
		lblRynekSurowcw.setBounds(330, 10, 100, 13);
		contentPane.add(lblRynekSurowcw);
		
		JLabel lblInfoOSurowcu = new JLabel("Informacje o surowcu:");
		lblInfoOSurowcu.setBounds(10, 608, 150, 13);
		contentPane.add(lblInfoOSurowcu);
		
		JTextArea textAreaInfoOSurowcu = new JTextArea();
		textAreaInfoOSurowcu.setBounds(10, 626, 814, 32);
		contentPane.add(textAreaInfoOSurowcu);
		
		txtRynekSurowcw = new JTextField();
		txtRynekSurowcw.setBounds(330, 30, 150, 19);
		contentPane.add(txtRynekSurowcw);
		txtRynekSurowcw.setColumns(10);
		txtRynekSurowcw.setEditable(false);
		if(!Swiat.listaWsyztskichRynkowSurowcow.isEmpty()) {
			txtRynekSurowcw.setText(Swiat.listaWsyztskichRynkowSurowcow.get(0).getNazwa());
		}else {txtRynekSurowcw.setText("Brak rynku surowc�w!");}
		
		JLabel lblSurowce = new JLabel("Surowce:");
		lblSurowce.setBounds(330, 54, 65, 13);
		contentPane.add(lblSurowce);
		
		JScrollPane scrollPaneSurowce = new JScrollPane();
		scrollPaneSurowce.setBounds(330, 74, 150, 350);
		contentPane.add(scrollPaneSurowce);
			
		JList<Surowiec> listSurowce = new JList<>();
		listSurowce.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneSurowce.setViewportView(listSurowce);
		DefaultListModel<Surowiec> modelListSurowce = new DefaultListModel<>();
		if(!Swiat.listaWsyztskichRynkowSurowcow.isEmpty()) {//je�eli istnieje rynek, to wy�wietla wsyztskie surowce
			for (Surowiec s : RynekSurowcow.listaWszystkichSurowcow) {
				modelListSurowce.addElement(s);
			}
		}
		listSurowce.setModel(modelListSurowce);
		
		listSurowce.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {			
				JList source = (JList)arg0.getSource();
				Surowiec s = (Surowiec)source.getSelectedValue();
				textAreaInfoOSurowcu.setText("Surowiec: "+s.getNazwa()+"   Jednostka handlowa: "+s.getJednostkaHandlowa()+"   Waluta: "+s.getWaluta()+
						"\nAktualna warto��: "+s.getAktualnaWartosc()+"   Minimalna warto��: "+s.getMinWartosc()+"   Maksymalna warto��: "+
						s.getMaxWartosc());
			}
		});	
		
		
		btnWykup.addActionListener(new ActionListener() {//sp�ka wykupuje swoje akcje za jak�� kwot�, te znikaj� z rynku 
			public void actionPerformed(ActionEvent arg0) {
				
				synchronized (Swiat.monitor) {
				
					Akcja a = wybranaSpolka.getAkcja();//akcja sp�ki zaznaczonej na li�cie
					double cenaWykupu = Swiat.zaokraglijDo2miejsc(Double.parseDouble(txtCenaWykupu.getText()));//podaje za jak� kwot� chce wykupi� akcje
					int liczbaAkcjiDoWykupu = (int)(cenaWykupu/a.getAktualnyKurs());//liczy ile akcji za to wykupi�
					if(liczbaAkcjiDoWykupu > wybranaSpolka.getLiczbaAkcji()) txtCenaWykupu.setText("Nie ma tylu akcji");
					else {
						wybranaSpolka.setLiczbaAkcji(wybranaSpolka.getLiczbaAkcji()-liczbaAkcjiDoWykupu);
						txtCenaWykupu.setText("");
					}	
				}
			}
		});
		
		JLabel lblInfoOInwestorzeLubFunduszu = new JLabel("Informacje o inwestorze/funduszu:");
		lblInfoOInwestorzeLubFunduszu.setBounds(835, 54, 211, 13);
		contentPane.add(lblInfoOInwestorzeLubFunduszu);
		
		JScrollPane scrollPaneDaneInwestora = new JScrollPane();
		scrollPaneDaneInwestora.setBounds(835, 75, 170, 348);
		contentPane.add(scrollPaneDaneInwestora);
		
		JTextArea textAreaDaneInwestora = new JTextArea();
		scrollPaneDaneInwestora.setViewportView(textAreaDaneInwestora);
		
//				JLabel lblInfoOInwestorze = new JLabel("Informacje o inwestorze:");
//				lblInfoOInwestorze.setBounds(10, 668, 138, 13);
//				contentPane.add(lblInfoOInwestorze);
//				
//				JTextArea textAreaInfoOInwestorze = new JTextArea();
//				textAreaInfoOInwestorze.setBounds(10, 686, 814, 32);
//				contentPane.add(textAreaInfoOInwestorze);
//				
//				JLabel lblInfoOFunduszu = new JLabel("Informacje o funduszu inwestycyjnym:");
//				lblInfoOFunduszu.setBounds(10, 728, 235, 13);
//				contentPane.add(lblInfoOFunduszu);
//				
//				JTextArea textAreaInfoOFunduszu = new JTextArea();
//				textAreaInfoOFunduszu.setBounds(10, 751, 814, 32);
//				contentPane.add(textAreaInfoOFunduszu);
		
		JScrollPane scrollPaneInwestorzy = new JScrollPane();
		scrollPaneInwestorzy.setBounds(514, 74, 150, 348);
		contentPane.add(scrollPaneInwestorzy);
		
		JList<InwestorIndywidualny> listInwestorzy = new JList<>();
		listInwestorzy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneInwestorzy.setViewportView(listInwestorzy);
			DefaultListModel<InwestorIndywidualny> modelListInwestorzy = new DefaultListModel<>();
			if(!Swiat.listaWszytskichInwestorowIndywidualnych.isEmpty()) {
				for (InwestorIndywidualny inw : Swiat.listaWszytskichInwestorowIndywidualnych) {
					modelListInwestorzy.addElement(inw);
				}
			}
			listInwestorzy.setModel(modelListInwestorzy);
			
		listInwestorzy.addMouseListener(new MouseAdapter() {//WY�WIETLA INFO O INWESTORZE
			@Override
			public void mouseClicked(MouseEvent arg0) {
				synchronized (Swiat.monitor) {
					JList source = (JList)arg0.getSource();
					InwestorIndywidualny inw = (InwestorIndywidualny)source.getSelectedValue();
					//textAreaInfoOInwestorze.setText(inw.getImie()+" "+inw.getNazwisko()+" PESEL: "+inw.getPesel()+" Bud�et: "+inw.getBudzet());
					textAreaDaneInwestora.setText(inw.getImie()+" "+inw.getNazwisko()+"\nPESEL: "+inw.getPesel()+"\nBud�et: "+inw.getBudzet());
					textAreaDaneInwestora.append("\n\nPosiadane akcje:");
					for (Akcja a : inw.getlWykupioneAkcje()) {
						textAreaDaneInwestora.append("\n"+a);
						int liczbaAkcji = inw.getlLiczbaAkcji().get( inw.getlWykupioneAkcje().indexOf(a) );
						textAreaDaneInwestora.append(" "+liczbaAkcji);
					}
					textAreaDaneInwestora.append("\n\nPosiadane surowce:");
					for (Surowiec s : inw.getlWykupioneSurowce()) {
						textAreaDaneInwestora.append("\n"+s);
						int liczbaSurowca = inw.getlLiczbaSurowc�w().get( inw.getlWykupioneSurowce().indexOf(s) );
						textAreaDaneInwestora.append(" "+liczbaSurowca);
					}
					textAreaDaneInwestora.append("\n\nPosiadane waluty:");
					for (Waluta w : inw.getlWykupioneWaluty()) {
						textAreaDaneInwestora.append("\n"+w);
						int liczbaWaluty = inw.getlLiczbaWalut().get( inw.getlWykupioneWaluty().indexOf(w) );
						textAreaDaneInwestora.append(" "+liczbaWaluty);
					}
					textAreaDaneInwestora.append("\n\nPosiadane jednostki funduszy:");
					for (FunduszInwestycyjny fun : inw.getlWykupioneJednostkiFunduszy()) {
						textAreaDaneInwestora.append("\n"+fun);
						int liczbaJednostek = inw.getlLiczbaJednostekFunduszy().get( inw.getlWykupioneJednostkiFunduszy().indexOf(fun) );
						textAreaDaneInwestora.append(" "+liczbaJednostek);
					}
				}
			}
		});
			
		
		JLabel lblInwestorzy = new JLabel("Inwestorzy:");
		lblInwestorzy.setBounds(512, 54, 65, 13);
		contentPane.add(lblInwestorzy);
		
		
		JScrollPane scrollPaneFundusze = new JScrollPane();
		scrollPaneFundusze.setBounds(674, 74, 150, 348);
		contentPane.add(scrollPaneFundusze);
		
		JList<FunduszInwestycyjny> listFundusze = new JList<>();
		listFundusze.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneFundusze.setViewportView(listFundusze);
			DefaultListModel<FunduszInwestycyjny> modelListFundusze = new DefaultListModel<>();
			if(!Swiat.listaWszytskichFunduszyInwestycyjnych.isEmpty()) {
				for (FunduszInwestycyjny fun : Swiat.listaWszytskichFunduszyInwestycyjnych) {
					modelListFundusze.addElement(fun);
				}
			}
			listFundusze.setModel(modelListFundusze);
			
		listFundusze.addMouseListener(new MouseAdapter() {//WY�WIETLA INFO O FUNDUSZU
			@Override
			public void mouseClicked(MouseEvent arg0) {
				synchronized (Swiat.monitor) {
					JList source = (JList)arg0.getSource();
					FunduszInwestycyjny fun = (FunduszInwestycyjny)source.getSelectedValue();
	//				textAreaInfoOFunduszu.setText(fun.getNazwa()+"  Osoba zarz�dzaj�ca: "+fun.getImie()+" "+fun.getNazwisko()+"\nZysk: "+fun.getZysk()+
	//						"  Liczba dost�pnych jednostek: "+fun.getDostepneJednostkiFunduszu()+"  Koszt jednostki: "+fun.getKosztJednostki());
					
					textAreaDaneInwestora.setText(fun.getNazwa()+"\nOsoba zarz�zaj�ca:\n"+fun.getImie()+" "+fun.getNazwisko()+"\nZysk: "+fun.getZysk());
					textAreaDaneInwestora.append("\n\nPosiadane akcje:");
					for (Akcja a : fun.getlWykupioneAkcje()) {
						textAreaDaneInwestora.append("\n"+a);
						int liczbaAkcji = fun.getlLiczbaAkcji().get( fun.getlWykupioneAkcje().indexOf(a) );
						textAreaDaneInwestora.append(" "+liczbaAkcji);
					}
					textAreaDaneInwestora.append("\n\nPosiadane surowce:");
					for (Surowiec s : fun.getlWykupioneSurowce()) {
						textAreaDaneInwestora.append("\n"+s);
						int liczbaSurowca = fun.getlLiczbaSurowc�w().get( fun.getlWykupioneSurowce().indexOf(s) );
						textAreaDaneInwestora.append(" "+liczbaSurowca);
					}
					textAreaDaneInwestora.append("\n\nPosiadane waluty:");
					for (Waluta w : fun.getlWykupioneWaluty()) {
						textAreaDaneInwestora.append("\n"+w);
						int liczbaWaluty = fun.getlLiczbaWalut().get( fun.getlWykupioneWaluty().indexOf(w) );
						textAreaDaneInwestora.append(" "+liczbaWaluty);
					}
				}
			}
		});
		
		JLabel lblFunduszeInwestycyjne = new JLabel("Fundusze inwestycyjne:");
		lblFunduszeInwestycyjne.setBounds(674, 54, 136, 13);
		contentPane.add(lblFunduszeInwestycyjne);
		
			
		JButton btnOdswiezAktywa = new JButton("Od�wie� aktywa");
		btnOdswiezAktywa.setBounds(330, 434, 150, 21);
		contentPane.add(btnOdswiezAktywa);
		
		JButton btnOdswiezInwestorow = new JButton("Od\u015Bwie\u017C inwestor\u00F3w");
		btnOdswiezInwestorow.setBounds(589, 433, 161, 21);
		contentPane.add(btnOdswiezInwestorow);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(835, 10, 100, 30);
		contentPane.add(btnStop);
		
		JButton btnZapisz = new JButton("Zapisz");
		btnZapisz.setBounds(946, 577, 100, 21);
		contentPane.add(btnZapisz);
		btnZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Swiat.zapisz();
				} catch (IOException e) {
					e.printStackTrace();
				}
								
			}
		});
		
		
		JButton btnWczytaj = new JButton("Wczytaj");
		btnWczytaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Swiat.wczytaj();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for (Gielda g : Swiat.listaWsyztskichGield) {
					System.out.println(g.getNazwa());
				}
				for (RynekWalut w : Swiat.listaWsyztskichRynkowWalut) {
					System.out.println(w.getNazwa());
				}
				for (RynekSurowcow s : Swiat.listaWsyztskichRynkowSurowcow) {
					System.out.println(s.getNazwa());
				}
				
			}
		});
		btnWczytaj.setBounds(946, 608, 100, 21);
		contentPane.add(btnWczytaj);
		
//		JButton btnStart = new JButton("Start");
//		btnStart.setBounds(945, 15, 85, 21);
//		btnStart.setEnabled(false);
//		contentPane.add(btnStart);
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (Swiat.monitor) {
					Swiat.stop=true;
					//btnStart.setEnabled(true);
				}
			}
		});
		
//		btnStart.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				synchronized (Swiat.monitor) {
//					Swiat.stop=false;
//					for (Spolka s : Gielda.listaWsyztskichSpolek) {
//						s.start();
//						//System.out.println(s+"  "+s.getState());
//					}
//					for (InwestorIndywidualny inw : Swiat.listaWszytskichInwestorowIndywidualnych) {
//						Thread watekInwestora = new Thread(inw);
//						watekInwestora.start();
//						//System.out.println(inw+"  "+((Thread)watekInwestora).getState());
//					}
//					for (FunduszInwestycyjny fun : Swiat.listaWszytskichFunduszyInwestycyjnych) {
//						Thread watekFunduszu = new Thread(fun);
//						watekFunduszu.start();
//						//System.out.println(fun+"  "+((Thread)watekFunduszu).getState());
//					}
//				}
//			}
//		});
		
		
		btnOdswiezAktywa.addActionListener(new ActionListener() {//od�wie�anie aktyw�w i Rynk�w i tworzenie inwestor�w
			public void actionPerformed(ActionEvent arg0) {
				
				synchronized (Swiat.monitor) {
				
					Random random = new Random();
					
					//RYNKI
					if(!Swiat.listaWsyztskichGield.isEmpty()) {
						txtGie�da.setText(Swiat.listaWsyztskichGield.get(0).getNazwa());
					}else {txtGie�da.setText("Brak gie�dy!");}
					
					if(!Swiat.listaWsyztskichRynkowWalut.isEmpty()) {
						txtRynekWalut.setText(Swiat.listaWsyztskichRynkowWalut.get(0).getNazwa());
					}else {txtRynekWalut.setText("Brak rynku walut!!");}
					
					if(!Swiat.listaWsyztskichRynkowSurowcow.isEmpty()) {
						txtRynekSurowcw.setText(Swiat.listaWsyztskichRynkowSurowcow.get(0).getNazwa());
					}else {txtRynekSurowcw.setText("Brak rynku surowc�w!");}
					
					
					
					//LISTA AKCJI
					if(!Swiat.listaWsyztskichGield.isEmpty()) {//je�eli istnieje gie�da
						modelListAkcje.removeAllElements();//usuwa wszystkie elementy, �eby si� nie duplikowa�y
						for (Spolka s : Gielda.listaWsyztskichSpolek) {//dodaje od nowa elementy
							modelListAkcje.addElement(s);
						}
					}else {txtGie�da.setText("Brak gie�dy!");}
					listAkcje.setModel(modelListAkcje);			
					//LISTA WALUT
					if(!Swiat.listaWsyztskichRynkowWalut.isEmpty()) {//je�eli istnieje gie�da
						modelListWaluty.removeAllElements();
						for (Waluta w : RynekWalut.listaWszytskichWalut) {//dodaje od nowa elementy
							modelListWaluty.addElement(w);
						}
					}else {txtRynekWalut.setText("Brak rynku walut!");}
					listAkcje.setModel(modelListAkcje);
					//LISTA SUROWC�W
					if(!Swiat.listaWsyztskichRynkowSurowcow.isEmpty()) {//je�eli istnieje gie�da
						modelListSurowce.removeAllElements();
						for (Surowiec s : RynekSurowcow.listaWszystkichSurowcow) {//dodaje od nowa elementy
							modelListSurowce.addElement(s);
						}
					}else {txtRynekSurowcw.setText("Brak rynku walut!");}
					listSurowce.setModel(modelListSurowce);
					
					
					if(Swiat.przyrostAktywowNaRynkach>=100000) {
						//TWORZENIE INWESTOR�W
						long liczbaNowychInwestor�w = Swiat.przyrostAktywowNaRynkach/100000;//jeden inwestor i fundusz na ka�de 100.000 aktyw�w
																							//ka�da waluta i surowiec licz� si� jako 40.000 aktyw�w
						//System.out.println("Przyrost aktyw�w: "+Swiat.przyrostAktywowNaRynkach+"   Liczba nowych inwestor�w: "+liczbaNowychInwestor�w);				
						for (int i = 0; i < liczbaNowychInwestor�w; i++) {
								//System.out.println("Wykonanie p�tli "+i);
							InwestorIndywidualny inwestor = new InwestorIndywidualny();//tworzenie inwestora i losowanie jego p�l	
							int indeks = random.nextInt(Inwestor.bazaImion.length);
							inwestor.setImie(Inwestor.bazaImion[indeks]);	
							
							indeks = random.nextInt(Inwestor.bazaNazwisk.length);
							inwestor.setNazwisko(Inwestor.bazaNazwisk[indeks]);	
							
							String pesel = "";
							for (int j = 0; j < 11; j++) {
								indeks = random.nextInt(57-48+1)+48;//losuje cyfr�
								pesel += Character.toString ((char) indeks );
							}
							inwestor.setPesel(pesel);
							
							double budzet = Swiat.zaokraglijDo2miejsc( Math.random()*(1000000-100+1)+100 );//od 100 do 1.000.000
							inwestor.setBudzet(budzet);
							
							Swiat.listaWszytskichInwestorowIndywidualnych.add(inwestor);
			
							//utworzenie w�tku inwestora indywidualnego (start dalej)
							Thread watekInwestora = new Thread(inwestor);
							
							FunduszInwestycyjny fundusz = new FunduszInwestycyjny();//tworzenie funduszu i losowanie jego p�l
							String nazwaFunduszu="Fundusz ";						
							int x = random.nextInt(90-65+1)+65;//losuje wielk� liter�
							nazwaFunduszu += Character.toString ((char) x);//wielka litera na pocz�tek
							int dlg = random.nextInt(15-4+1)+4;//losuje d�ugo�� nazwy 4-15
							for(int j =1 ; j<dlg ; j++) {
								x = random.nextInt(122-97+1)+97;//losuje ma�� liter�
								nazwaFunduszu +=  Character.toString ((char) x);
							}
							fundusz.setNazwa(nazwaFunduszu);
							
							indeks = random.nextInt(Inwestor.bazaImion.length);
							fundusz.setImie(Inwestor.bazaImion[indeks]);
							
							indeks = random.nextInt(Inwestor.bazaNazwisk.length);
							fundusz.setNazwisko(Inwestor.bazaNazwisk[indeks]);		
							
							Swiat.listaWszytskichFunduszyInwestycyjnych.add(fundusz);
							
							//utworzenie w�tku funduszu
							Thread watekFunduszu = new Thread(fundusz);
							
							//START W�TK�W
							watekInwestora.start();
							watekFunduszu.start();
							
							Swiat.przyrostAktywowNaRynkach=0;//zeruje, je�eli pojawili si� nowi inwestorzy
						}				
					}
					
					//TEST
	//				System.out.println("Inwestorzy: ");
	//				for (InwestorIndywidualny inw : Swiat.listaWszytskichInwestorowIndywidualnych) {
	//					System.out.println(" - "+inw.getImie()+" "+inw.getNazwisko());
	//				}
	//				System.out.println("Fundusze: ");
	//				for (FunduszInwestycyjny fun : Swiat.listaWszytskichFunduszyInwestycyjnych) {
	//					System.out.println(" - "+fun.getNazwa());
//					}
				
				}
			}
		});
		
		
		btnOdswiezInwestorow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//LISTA INWESTOR�W
				if(!Swiat.listaWszytskichInwestorowIndywidualnych.isEmpty()) {
					modelListInwestorzy.removeAllElements();
					for (InwestorIndywidualny inw : Swiat.listaWszytskichInwestorowIndywidualnych) {
						modelListInwestorzy.addElement(inw);
					}
				}	
				listInwestorzy.setModel(modelListInwestorzy);
				
				//LISTA WALUT
				if(!Swiat.listaWszytskichFunduszyInwestycyjnych.isEmpty()) {
					modelListFundusze.removeAllElements();
					for (FunduszInwestycyjny fun : Swiat.listaWszytskichFunduszyInwestycyjnych) {
						modelListFundusze.addElement(fun);
					}
				}
				listFundusze.setModel(modelListFundusze);
				
			}
		});
		
		
		
					
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {								
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							OknoWprowadzaniaNowychObiektow owno = new OknoWprowadzaniaNowychObiektow();
							owno.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
												
			}
		});
		
	}
}
