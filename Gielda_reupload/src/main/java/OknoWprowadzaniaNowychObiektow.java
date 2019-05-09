package main.java;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.LineNumberInputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;

public class OknoWprowadzaniaNowychObiektow extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textFieldDataWyceny;
	private JTextField textFieldKursOtwarcia;
	private JTextField textFieldAktualnyKurs;
	private JTextField textFieldMinKurs;
	private JTextField textFieldMaxKurs;
	
	private int wyborObiektu;
	private List <Spolka> listaWybranychElementow;	
	private String[] tablicaKrajowDlaWaluty;
	private Waluta wylosowanaWaluta;
	//private Rynek wyborRynku;
		
// Create the frame.	 
	public OknoWprowadzaniaNowychObiektow() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 700, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//inicjalizacja pól tekstowych i etykiet
		JLabel lblCoChceszDodac = new JLabel("Co chcesz dodaæ?");
		lblCoChceszDodac.setBounds(15, 15, 110, 20);
		contentPane.add(lblCoChceszDodac);
				
		textField_1 = new JTextField();
		textField_1.setBounds(145, 55, 190, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(40);
		textField_1.setVisible(false);
				
		JLabel lblNewLabel_1 = new JLabel("Nazwa");
		lblNewLabel_1.setBounds(15, 58, 120, 13);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
					
		textField_2 = new JTextField();
		textField_2.setBounds(145, 85, 190, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(40);
		textField_2.setVisible(false);
				
		JLabel lblNewLabel_2 = new JLabel("Kraj");
		lblNewLabel_2.setBounds(15, 88, 120, 13);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
				
		textField_3 = new JTextField();
		textField_3.setBounds(145, 115, 190, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(40);
		textField_3.setVisible(false);
				
		JLabel lblNewLabel_3 = new JLabel("Waluta");
		lblNewLabel_3.setBounds(15, 118, 120, 13);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
				
		textField_4 = new JTextField();
		textField_4.setBounds(145, 145, 190, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(40);
		textField_4.setVisible(false);
				
		JLabel lblNewLabel_4 = new JLabel("Miasto");
		lblNewLabel_4.setBounds(15, 148, 120, 13);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);
				
		textField_5 = new JTextField();
		textField_5.setBounds(145, 175, 190, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(40);
		textField_5.setVisible(false);
				
		JLabel lblNewLabel_5 = new JLabel("Adres");
		lblNewLabel_5.setBounds(15, 178, 120, 13);
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);
				
		textField_6 = new JTextField();
		textField_6.setBounds(145, 205, 190, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(40);
		textField_6.setVisible(false);
				
		JLabel lblNewLabel_6 = new JLabel("Mar¿a");
		lblNewLabel_6.setBounds(15, 208, 120, 13);
		contentPane.add(lblNewLabel_6);
		lblNewLabel_6.setVisible(false);
				
		textField_7 = new JTextField();
		textField_7.setBounds(145, 235, 190, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(40);
		textField_7.setVisible(false);
		textField_7.setVisible(false);
				
		JLabel lblNewLabel_7 = new JLabel("label7");
		lblNewLabel_7.setBounds(15, 238, 120, 13);
		contentPane.add(lblNewLabel_7);
		lblNewLabel_7.setVisible(false);
				
		textField_8 = new JTextField();
		textField_8.setBounds(145, 265, 190, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(40);
		textField_8.setVisible(false);
				
		JLabel lblNewLabel_8 = new JLabel("label8");
		lblNewLabel_8.setBounds(15, 268, 120, 13);
		contentPane.add(lblNewLabel_8);
		lblNewLabel_8.setVisible(false);		
				
		JLabel lblDataWyceny = new JLabel("Data pierwszej wyceny");
		lblDataWyceny.setBounds(350, 58, 130, 13);
		contentPane.add(lblDataWyceny);
		lblDataWyceny.setVisible(false);
		
		JLabel lblKursOtwarcia = new JLabel("Kurs otwarcia");
		lblKursOtwarcia.setBounds(350, 88, 100, 13);
		contentPane.add(lblKursOtwarcia);
		lblKursOtwarcia.setVisible(false);
		
		JLabel lblAktualnyKurs = new JLabel("Aktualny kurs");
		lblAktualnyKurs.setBounds(350, 118, 100, 13);
		contentPane.add(lblAktualnyKurs);
		lblAktualnyKurs.setVisible(false);
		
		JLabel lblMinKurs = new JLabel("Minimalny kurs");
		lblMinKurs.setBounds(350, 148, 100, 13);
		contentPane.add(lblMinKurs);
		lblMinKurs.setVisible(false);
		
		JLabel lblMaxKurs = new JLabel("Maksymalny kurs");
		lblMaxKurs.setBounds(350, 178, 100, 13);
		contentPane.add(lblMaxKurs);
		lblMaxKurs.setVisible(false);
		
		textFieldDataWyceny = new JTextField();
		textFieldDataWyceny.setBounds(480, 55, 96, 19);
		contentPane.add(textFieldDataWyceny);
		textFieldDataWyceny.setColumns(10);
		textFieldDataWyceny.setVisible(false);
		
		textFieldKursOtwarcia = new JTextField();
		textFieldKursOtwarcia.setBounds(480, 85, 96, 19);
		contentPane.add(textFieldKursOtwarcia);
		textFieldKursOtwarcia.setColumns(10);
		textFieldKursOtwarcia.setVisible(false);
		
		textFieldAktualnyKurs = new JTextField();
		textFieldAktualnyKurs.setBounds(480, 115, 96, 19);
		contentPane.add(textFieldAktualnyKurs);
		textFieldAktualnyKurs.setColumns(10);
		textFieldAktualnyKurs.setVisible(false);
		
		textFieldMinKurs = new JTextField();
		textFieldMinKurs.setBounds(480, 145, 96, 19);
		contentPane.add(textFieldMinKurs);
		textFieldMinKurs.setColumns(10);
		textFieldMinKurs.setVisible(false);
		
		textFieldMaxKurs = new JTextField();
		textFieldMaxKurs.setBounds(480, 178, 96, 19);
		contentPane.add(textFieldMaxKurs);
		textFieldMaxKurs.setColumns(10);
		textFieldMaxKurs.setVisible(false);
		
		JTextArea textAreaKraje = new JTextArea();
		textAreaKraje.setBounds(145, 145, 190, 60);
		contentPane.add(textAreaKraje);
		textAreaKraje.setVisible(false);
		textAreaKraje.setEditable(false);
		textAreaKraje.setLineWrap(true);
		textAreaKraje.setWrapStyleWord(true);
		
		JButton btnDodajObiekt = new JButton("Dodaj obiekt");		
		btnDodajObiekt.setBounds(554, 15, 120, 20);
		contentPane.add(btnDodajObiekt);
		btnDodajObiekt.setEnabled(false);
		
		JLabel lblRynek = new JLabel("Rynek:");
		lblRynek.setBounds(350, 19, 45, 13);
		contentPane.add(lblRynek);
				
		JComboBox comboBoxRynek = new JComboBox();
		comboBoxRynek.setBounds(389, 15, 155, 20);
		contentPane.add(comboBoxRynek);
		
		//lista spó³ek do tworzenia Indeksów
		JList<Spolka> listSpolki  = new JList<>();		
		listSpolki.setVisibleRowCount(20);
		listSpolki.setBounds(450, 55, 130, 225);
		contentPane.add(listSpolki);	
		listSpolki.setVisible(false);
		listSpolki.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//mo¿na wybieraæ wiele obiektów
		listSpolki.setLayoutOrientation(JList.VERTICAL);
		
		//listener do wyboru spó³ek z listy przy dodawaniu indeksów
		listSpolki.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!arg0.getValueIsAdjusting()) {
					listaWybranychElementow = listSpolki.getSelectedValuesList();
					//System.out.println(listaWybranychElementow);
				}
			}
		});	
		
		//comboBox obiektów
		String[] comboDodawanie = {"Gie³da papierów wartoœciowych","Rynek walut","Rynek surowców","Indeks","Spó³ka","Waluta","Surowiec",
				"Inwestor indywidualny","Fundusz inwestycyjny"};
		
		JComboBox comboBox = new JComboBox(comboDodawanie);
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(146, 15, 190, 20);
		comboBox.setMaximumRowCount(10);//ustala ile wierszy wyœwietla combobox
		contentPane.add(comboBox);											
		
		JScrollPane scrollPaneListaSpolek = new JScrollPane(listSpolki);
		scrollPaneListaSpolek.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneListaSpolek.setBounds(450, 55, 140, 225);
		contentPane.add(scrollPaneListaSpolek);
		scrollPaneListaSpolek.setVisible(false);
				
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				JComboBox source = (JComboBox)arg0.getSource();
				wyborObiektu = source.getSelectedIndex();
				
				Random random = new Random();//do losowania 
				
				if (arg0.getStateChange()==ItemEvent.SELECTED) {//akcja wywo³ana zaznaczeniem elementu ComboBoxa
					
					if (wyborObiektu==0) {//Gie³ada papierów wartoœciowych					
						lblNewLabel_1.setText("Nazwa");
						lblNewLabel_1.setVisible(true);
						textField_1.setVisible(true);
						lblNewLabel_2.setText("Kraj");
						lblNewLabel_2.setVisible(true);
						textField_2.setVisible(true);
						lblNewLabel_3.setText("Waluta");
						lblNewLabel_3.setVisible(true);
						textField_3.setVisible(true);
						lblNewLabel_4.setText("Miasto");
						lblNewLabel_4.setVisible(true);
						textField_4.setVisible(true);
						lblNewLabel_5.setText("Adres");
						lblNewLabel_5.setVisible(true);
						textField_5.setVisible(true);
						lblNewLabel_6.setText("Mar¿a");
						lblNewLabel_6.setVisible(true);
						textField_6.setVisible(true);
						btnDodajObiekt.setEnabled(true);
					}
					else if (wyborObiektu==1 || wyborObiektu==2) {//Rynek walut lub Rynek Surowców (maj¹ takie same pola)
						lblNewLabel_1.setText("Nazwa");
						lblNewLabel_1.setVisible(true);
						textField_1.setVisible(true);
						lblNewLabel_2.setText("Kraj");
						lblNewLabel_2.setVisible(true);
						textField_2.setVisible(true);
						lblNewLabel_4.setText("Miasto");
						lblNewLabel_4.setVisible(true);
						textField_4.setVisible(true);
						lblNewLabel_5.setText("Adres");
						lblNewLabel_5.setVisible(true);
						textField_5.setVisible(true);
						lblNewLabel_6.setText("Mar¿a");
						lblNewLabel_6.setVisible(true);
						textField_6.setVisible(true);
						btnDodajObiekt.setEnabled(true);
					}	
					else if (wyborObiektu==3) {//Indeks
						lblNewLabel_1.setText("Nazwa");
						lblNewLabel_1.setVisible(true);
						textField_1.setVisible(true);
						lblNewLabel_2.setText("Wynik indeksu");//obliczany na podsawie wartoœci spó³ek nale¿¹cych do indeksu
						lblNewLabel_2.setVisible(true);
						textField_2.setVisible(true);
						textField_2.setEditable(false);						
						lblDataWyceny.setText("Wybór spó³ek");
						lblDataWyceny.setVisible(true);
						listSpolki.setVisible(true);
						scrollPaneListaSpolek.setVisible(true);
						btnDodajObiekt.setEnabled(true);
								
						DefaultListModel<Spolka> modelListy = new DefaultListModel<>();												
						for (Spolka spolka : Gielda.listaWsyztskichSpolek) {//dodaje do listy wszytskie spó³ki z rynku
							modelListy.addElement(spolka);	
						}
						listSpolki.setModel(modelListy);
					}
					else if (wyborObiektu==4) {//Spó³ka						
						lblNewLabel_1.setText("Nazwa");
						lblNewLabel_1.setVisible(true);
						textField_1.setVisible(true);
						textField_1.setEditable(false);
						lblNewLabel_2.setText("Liczba akcji");
						lblNewLabel_2.setVisible(true);
						textField_2.setVisible(true);
						textField_2.setEditable(false);		
						lblNewLabel_3.setText("Zysk");
						lblNewLabel_3.setVisible(true);
						textField_3.setVisible(true);
						textField_3.setEditable(false);
						lblNewLabel_4.setText("Przychód");
						lblNewLabel_4.setVisible(true);
						textField_4.setVisible(true);
						textField_4.setEditable(false);
						lblNewLabel_5.setText("Kapita³ w³asny");
						lblNewLabel_5.setVisible(true);
						textField_5.setVisible(true);
						textField_5.setEditable(false);
						lblNewLabel_6.setText("Kapita³ zak³adowy");
						lblNewLabel_6.setVisible(true);
						textField_6.setVisible(true);
						textField_6.setEditable(false);
						lblNewLabel_7.setText("Wolumen");
						lblNewLabel_7.setVisible(true);
						textField_7.setVisible(true);
						textField_7.setEditable(false);
						lblNewLabel_8.setText("Obroty");
						lblNewLabel_8.setVisible(true);
						textField_8.setVisible(true);
						textField_8.setEditable(false);							
						lblDataWyceny.setVisible(true);
						lblKursOtwarcia.setVisible(true);
						lblAktualnyKurs.setVisible(true);
						lblMinKurs.setVisible(true);
						lblMaxKurs.setVisible(true);
						textFieldDataWyceny.setVisible(true);
						textFieldDataWyceny.setEditable(false);	
						textFieldKursOtwarcia.setVisible(true);
						textFieldKursOtwarcia.setEditable(false);
						textFieldAktualnyKurs.setVisible(true);
						textFieldAktualnyKurs.setEditable(false);
						textFieldMinKurs.setVisible(true);
						textFieldMinKurs.setEditable(false);
						textFieldMaxKurs.setVisible(true);
						textFieldMaxKurs.setEditable(false);
						btnDodajObiekt.setEnabled(true);
						
						//losowanie wszytskich pól
						String nazwaSpolki = Spolka.losujNazweSpolki();
						textField_1.setText(nazwaSpolki);
						
						int liczbaAkcji = random.nextInt(100000)+1;//od 0 do 100000
						textField_2.setText(liczbaAkcji+"");
						
						double przychod = Swiat.zaokraglijDo2miejsc(Math.random() * 10000000); // od <0 do 10.000.000)						
						textField_4.setText(przychod+"");
						
						double zysk = Swiat.zaokraglijDo2miejsc( Math.random() * przychod);//od 0 do "przychod"
						textField_3.setText(zysk+"");
						
						double kapitalWlasny = Swiat.zaokraglijDo2miejsc( Math.random()*10000000);// od 0 do 10.000.000
						textField_5.setText(kapitalWlasny+"");
						
						double kapitalZakladowy = Swiat.zaokraglijDo2miejsc( Math.random()*(kapitalWlasny - 10000) + 10000 );//od 10000 do "kapitalWlasny"
						textField_6.setText(kapitalZakladowy+"");
						
						int wolumen = (int)(random.nextInt(liczbaAkcji+1) * 0.1);// max 10% liczby akcji
						textField_7.setText(wolumen+"");
												
						//TWORZENIE AKCJI SPÓ£KI (klasa Akcja)
						String dataWyceny = "dd.mm.yyyy";///DO UZUPE£NIENIA NA LOSOW¥ DATÊ
						textFieldDataWyceny.setText(dataWyceny);
						
						double maxKurs = Swiat.zaokraglijDo2miejsc( Math.random()*(300 - 0.05) +0.05 ); //od 0.05 do 500
						textFieldMaxKurs.setText(maxKurs+"");
						
						double minKurs = Swiat.zaokraglijDo2miejsc( Math.random()*(maxKurs - 0.01)+0.01 );//od 0.01 do maxKurs
						textFieldMinKurs.setText(minKurs+"");
						
						double kursOtwarcia = Swiat.zaokraglijDo2miejsc( Math.random()*(maxKurs - minKurs)+minKurs );
						textFieldKursOtwarcia.setText(kursOtwarcia+"");
						
						double aktualnyKurs = Swiat.zaokraglijDo2miejsc( Math.random()*(maxKurs - minKurs)+minKurs );
						textFieldAktualnyKurs.setText(aktualnyKurs+"");
												
						//OBROTY (z klasy Spó³ka)	
						double obroty = Swiat.zaokraglijDo2miejsc( (double)wolumen * aktualnyKurs );
						textField_8.setText(obroty+"");						
					}
					else if (wyborObiektu==5) {//Waluta						
						lblNewLabel_1.setText("Nazwa");
						lblNewLabel_1.setVisible(true);
						textField_1.setVisible(true);
						textField_1.setEditable(false);
						lblNewLabel_2.setText("Cena kupna");
						lblNewLabel_2.setVisible(true);
						textField_2.setVisible(true);
						textField_2.setEditable(false);		
						lblNewLabel_3.setText("Cena sprzeda¿y");
						lblNewLabel_3.setVisible(true);
						textField_3.setVisible(true);
						textField_3.setEditable(false);
						textAreaKraje.setVisible(true);
						btnDodajObiekt.setEnabled(true);
						
						//Nazwa ( skrót = 2-3 wielkie litery)
						int[] losNazwaWaluty = new int [3];
						for (int i = 0; i < losNazwaWaluty.length; i++) {
							losNazwaWaluty[i] = random.nextInt(90-65+1)+65;
						}
						String nazwaWaluty = Character.toString ((char) losNazwaWaluty[0]) + Character.toString ((char) losNazwaWaluty[1]);//nazwa 2 znaki
						if(random.nextBoolean()) { nazwaWaluty += Character.toString ((char) losNazwaWaluty[2]);}//50% szans, ¿e 3 znaki
						textField_1.setText(nazwaWaluty);
						
						double cenaKupna = Swiat.zaokraglijDo2miejsc( Math.random()*(15 - 0.05) + 0.05 );// od 0,05 do 15
						textField_2.setText(cenaKupna+"");
						
						double x = Swiat.zaokraglijDo2miejsc(Math.random());
						if (cenaKupna < 1.5 && x > 0.6) x = x - 0.5;//¿eby nie by³o du¿ej ró¿nicy przy ma³ej wartoœci
						else if (cenaKupna > 11) x = x + 0.6;
						double cenaSprzedazy = Swiat.zaokraglijDo2miejsc(cenaKupna+x);//cena sprzedarzy = powiêkszona cena kupna
						textField_3.setText(cenaSprzedazy+"");
						
						int liczbaKrajow = random.nextInt(5)+1;//losuje do 5 krajów
							int[] indeksyUzytychKrajow = new int [liczbaKrajow];
							for (int i = 0; i < indeksyUzytychKrajow.length; i++) {indeksyUzytychKrajow[i]=-1;}//"zerowanie" talbicy
							tablicaKrajowDlaWaluty = new String[liczbaKrajow];//tablica potrzebna do tworzenia obiektu przyciskiem
							boolean flaga = true;	
			//WYLOSOWA£O KRAJ NULL????				
						for (int j = 0; j < liczbaKrajow; j++) {//losowanie krajów z zabezpieczeniem przed powtórzeniami
							int indeksWaluty = random.nextInt( Waluta.bazaKrajów.length );//losuje indeks jednego kraju z bazy
							
							for (int k = 0; k < indeksyUzytychKrajow.length; k++) {//zabezpieczenie przed powtarzaniem
									if(indeksWaluty==indeksyUzytychKrajow[k]) {
										flaga=false;
										break;
										}
								}
							indeksyUzytychKrajow[j]=indeksWaluty;
							if(flaga) {//je¿eli kraj siê nie powtarza, to dodaje go do txtarea i do tablicy
									textAreaKraje.append(Waluta.bazaKrajów[indeksWaluty]+"");
									tablicaKrajowDlaWaluty[j]=Waluta.bazaKrajów[indeksWaluty];								
								}
							if(j<liczbaKrajow-1) {textAreaKraje.append(", ");}//dodaje przecinek po ka¿dym kraju, oprócz ostatniego
							flaga=true;
						}												
					}
					else if (wyborObiektu==6) {//Surowiec
						lblNewLabel_1.setText("Nazwa");
						lblNewLabel_1.setVisible(true);
						textField_1.setVisible(true);
						textField_1.setEditable(false);
						lblNewLabel_2.setText("Jednostka handlowa");
						lblNewLabel_2.setVisible(true);
						textField_2.setVisible(true);
						textField_2.setEditable(false);		
						lblNewLabel_3.setText("Waluta");
						lblNewLabel_3.setVisible(true);
						textField_3.setVisible(true);
						textField_3.setEditable(false);
						lblNewLabel_4.setText("Aktualna wartoœæ");
						lblNewLabel_4.setVisible(true);
						textField_4.setVisible(true);
						textField_4.setEditable(false);
						lblNewLabel_5.setText("Minimalna wartoœæ");
						lblNewLabel_5.setVisible(true);
						textField_5.setVisible(true);
						textField_5.setEditable(false);
						lblNewLabel_6.setText("Maksymalna wartoœæ");
						lblNewLabel_6.setVisible(true);
						textField_6.setVisible(true);
						textField_6.setEditable(false);
						btnDodajObiekt.setEnabled(true);
						
						//nazwa surowca - losowe 3-12 liter 
						String nazwaSurowca = "";
						int x = random.nextInt(90-65+1)+65;//losuje wielk¹ literê
						nazwaSurowca += Character.toString ((char) x);//wielka litera na pocz¹tek
						int dlg = random.nextInt(12-3+1)+3;//losuje d³ugoœæ nazwy
						for(int i =1 ; i<dlg ; i++) {
							x = random.nextInt(122-97+1)+97;//losuje ma³¹ literê
							nazwaSurowca +=  Character.toString ((char) x);
						}
						textField_1.setText(nazwaSurowca);
												
						int i = random.nextInt(Surowiec.bazaJednostekHandlowych.length);
						String jednostkaHandlowa = Surowiec.bazaJednostekHandlowych[i];
						textField_2.setText(jednostkaHandlowa);
											
						if(!RynekWalut.listaWszytskichWalut.isEmpty()) {
							i = random.nextInt( RynekWalut.listaWszytskichWalut.size() );
							wylosowanaWaluta = RynekWalut.listaWszytskichWalut.get(i);
							textField_3.setText(wylosowanaWaluta+"");
						} else textField_3.setText("Brak walut na rynku");
						
						double maxWartosc = Swiat.zaokraglijDo2miejsc( Math.random()*(5000-0.02)+0.02 );// 0,02-5000
						textField_6.setText(maxWartosc+"");
						
						double minWartosc = Swiat.zaokraglijDo2miejsc( Math.random()*(maxWartosc - 0.01)+0.01 );// 0,01-maxWartosc
						textField_5.setText(minWartosc+"");
						
						double aktualnaWartosc = Swiat.zaokraglijDo2miejsc( Math.random()*(maxWartosc - minWartosc)+minWartosc );
						textField_4.setText(aktualnaWartosc+"");					
					}
					else if (wyborObiektu==7) {//Inwestor indywidualny
						lblNewLabel_1.setText("Imiê");
						lblNewLabel_1.setVisible(true);
						textField_1.setVisible(true);
						textField_1.setEditable(false);
						lblNewLabel_2.setText("Nazwisko");
						lblNewLabel_2.setVisible(true);
						textField_2.setVisible(true);
						textField_2.setEditable(false);		
						lblNewLabel_3.setText("PESEL");
						lblNewLabel_3.setVisible(true);
						textField_3.setVisible(true);
						textField_3.setEditable(false);
						lblNewLabel_4.setText("Bud¿et");
						lblNewLabel_4.setVisible(true);
						textField_4.setVisible(true);
						textField_4.setEditable(false);
						btnDodajObiekt.setEnabled(true);
						
						int i = random.nextInt(Inwestor.bazaImion.length);
						String imieInwestor = Inwestor.bazaImion[i];
						textField_1.setText(imieInwestor);
						
						i = random.nextInt(Inwestor.bazaNazwisk.length);
						String nazwiskoInwestor = Inwestor.bazaNazwisk[i];
						textField_2.setText(nazwiskoInwestor);
						
						String pesel = "";
						for (int j = 0; j < 11; j++) {
							i = random.nextInt(57-48+1)+48;//losuje cyfrê
							pesel += Character.toString ((char) i );
						}
						textField_3.setText(pesel);
						
						double budzet = Swiat.zaokraglijDo2miejsc( Math.random()*(1000000-100+1)+100 );//od 100 do 1.000.000
						textField_4.setText(budzet+"");						
					}
					else if (wyborObiektu==8) {//Fundusz inwestycyjny
						lblNewLabel_1.setText("Nazwa");
						lblNewLabel_1.setVisible(true);
						textField_1.setVisible(true);
						textField_1.setEditable(false);
						lblNewLabel_2.setText("Imiê");
						lblNewLabel_2.setVisible(true);
						textField_2.setVisible(true);
						textField_2.setEditable(false);		
						lblNewLabel_3.setText("Nazwisko");
						lblNewLabel_3.setVisible(true);
						textField_3.setVisible(true);
						textField_3.setEditable(false);
						btnDodajObiekt.setEnabled(true);
						
						String nazwaFundusz="Fundusz ";						
						int x = random.nextInt(90-65+1)+65;//losuje wielk¹ literê
						nazwaFundusz += Character.toString ((char) x);//wielka litera na pocz¹tek
						int dlg = random.nextInt(15-4+1)+4;//losuje d³ugoœæ nazwy 4-15
						for(int i =1 ; i<dlg ; i++) {
							x = random.nextInt(122-97+1)+97;//losuje ma³¹ literê
							nazwaFundusz +=  Character.toString ((char) x);
						}
						textField_1.setText(nazwaFundusz);
						
						int i = random.nextInt(Inwestor.bazaImion.length);
						String imieFundusz = Inwestor.bazaImion[i];
						textField_2.setText(imieFundusz);
						
						i = random.nextInt(Inwestor.bazaNazwisk.length);
						String nazwiskoFundusz = Inwestor.bazaNazwisk[i];
						textField_3.setText(nazwiskoFundusz);
					}
					
				} else {//akcja wywo³ana odznaczaniem elementu ComboBoxa				
					lblNewLabel_1.setVisible(false);
					textField_1.setText("");
					textField_1.setVisible(false);
					textField_1.setEditable(true);
					lblNewLabel_2.setVisible(false);
					textField_2.setText("");
					textField_2.setVisible(false);
					textField_2.setEditable(true);
					lblNewLabel_3.setVisible(false);
					textField_3.setText("");
					textField_3.setVisible(false);
					textField_3.setEditable(true);
					lblNewLabel_4.setVisible(false);
					textField_4.setText("");
					textField_4.setVisible(false);
					textField_4.setEditable(true);
					lblNewLabel_5.setVisible(false);
					textField_5.setText("");
					textField_5.setVisible(false);
					textField_5.setEditable(true);
					lblNewLabel_6.setVisible(false);
					textField_6.setText("");
					textField_6.setVisible(false);
					textField_6.setEditable(true);
					lblNewLabel_7.setVisible(false);
					textField_7.setText("");
					textField_7.setVisible(false);
					textField_7.setEditable(true);
					lblNewLabel_8.setVisible(false);
					textField_8.setText("");
					textField_8.setVisible(false);	
					textField_8.setEditable(true);
					textAreaKraje.setText("");
					textAreaKraje.setVisible(false);					
					lblDataWyceny.setVisible(false);
					lblKursOtwarcia.setVisible(false);
					lblAktualnyKurs.setVisible(false);
					lblMinKurs.setVisible(false);
					lblMaxKurs.setVisible(false);
					textFieldDataWyceny.setText("");
					textFieldDataWyceny.setVisible(false);
					textFieldDataWyceny.setEditable(true);
					textFieldKursOtwarcia.setText("");
					textFieldKursOtwarcia.setVisible(false);					
					textFieldKursOtwarcia.setEditable(true);
					textFieldAktualnyKurs.setText("");
					textFieldAktualnyKurs.setVisible(false);
					textFieldAktualnyKurs.setEditable(true);
					textFieldMinKurs.setText("");
					textFieldMinKurs.setVisible(false);					
					textFieldMinKurs.setEditable(true);
					textFieldMaxKurs.setText("");
					textFieldMaxKurs.setVisible(false);
					textFieldMaxKurs.setEditable(true);							
					listSpolki.setVisible(false);
					scrollPaneListaSpolek.setVisible(false);
					btnDodajObiekt.setEnabled(false);
				}
			}
		});
		
		//listener przycisku dodawania obiektu
		btnDodajObiekt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(wyborObiektu==0) {//Gie³da papierów wartoœciowych
					if(!textField_1.getText().isEmpty() && !textField_2.getText().isEmpty() && !textField_3.getText().isEmpty() && 
							!textField_4.getText().isEmpty() && !textField_5.getText().isEmpty() && !textField_6.getText().isEmpty()) {						
						Gielda gielda = new Gielda();
						gielda.setNazwa(textField_1.getText());
						gielda.setKraj(textField_2.getText());
						gielda.setWaluta(textField_3.getText());
						gielda.setMiasto(textField_4.getText());
						gielda.setAdres(textField_5.getText());
						gielda.setMarza(Double.parseDouble(textField_6.getText()));
						Swiat.listaWsyztskichGield.add(gielda);											
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
						textField_5.setText("");
						textField_6.setText("");
					}else textField_1.setText("B³¹d, wype³nij wszytskie pola!");			
					
					//DO TESTOWANIA
//					for (Gielda g : Swiat.listaWsyztskichGield) {
//						System.out.println(g.getNazwa()+" "+g.getKraj()+" "+g.getMiasto()+" "+g.getAdres()+" "+g.getMarza()+" "+g.getWaluta());
//					}
					
				}
				else if (wyborObiektu==1) {//Rynek walut
					if(!textField_1.getText().isEmpty() && !textField_2.getText().isEmpty() && !textField_4.getText().isEmpty() && 
							!textField_5.getText().isEmpty() && !textField_6.getText().isEmpty()) {
						RynekWalut rynekWalut = new RynekWalut();
						rynekWalut.setNazwa(textField_1.getText());
						rynekWalut.setKraj(textField_2.getText());
						rynekWalut.setMiasto(textField_4.getText());
						rynekWalut.setAdres(textField_5.getText());
						rynekWalut.setMarza(Double.parseDouble(textField_6.getText()));
						Swiat.listaWsyztskichRynkowWalut.add(rynekWalut);
						textField_1.setText("");
						textField_2.setText("");
						textField_4.setText("");
						textField_5.setText("");
						textField_6.setText("");						
					}else textField_1.setText("B³¹d, wype³nij wszytskie pola!");	
					
					//DO TESTOWANIA
//					for (RynekWalut rw : Swiat.listaWsyztskichRynkowWalut) {
//						System.out.println(rw.getNazwa()+" "+rw.getKraj()+" "+rw.getMiasto()+" "+rw.getAdres()+" "+rw.getMarza());
//					}
					
				}
				else if (wyborObiektu==2) {//Rynek surowców
					if(!textField_1.getText().isEmpty() && !textField_2.getText().isEmpty() && !textField_4.getText().isEmpty() && 
							!textField_5.getText().isEmpty() && !textField_6.getText().isEmpty()) {
						RynekSurowcow rynekSurowcow = new RynekSurowcow();
						rynekSurowcow.setNazwa(textField_1.getText());
						rynekSurowcow.setKraj(textField_2.getText());
						rynekSurowcow.setMiasto(textField_4.getText());
						rynekSurowcow.setAdres(textField_5.getText());
						rynekSurowcow.setMarza(Double.parseDouble(textField_6.getText()));
						Swiat.listaWsyztskichRynkowSurowcow.add(rynekSurowcow);
						textField_1.setText("");
						textField_2.setText("");
						textField_4.setText("");
						textField_5.setText("");
						textField_6.setText("");						
					}else System.out.println("B³¹d, wype³nij wszytskie pola!");	
					
					//DO TESTOWANIA
//					for (RynekSurowcow rs : Swiat.listaWsyztskichRynkowSurowcow) {
//						System.out.println(rs.getNazwa()+" "+rs.getKraj()+" "+rs.getMiasto()+" "+rs.getAdres()+" "+rs.getMarza());
//					}
					
				}
				else if (wyborObiektu==3) {//Indeks
					if(!Swiat.listaWsyztskichGield.isEmpty()) {//je¿eli istnieje gie³da			
						if(!textField_1.getText().isEmpty()) {
							double wynik = 0;
							for (Spolka spolka : listaWybranychElementow) {
								wynik += spolka.getAkcja().getAktualnyKurs();
							}
							textField_2.setText(wynik+"");
							Indeks indeks = new Indeks(textField_1.getText(), wynik);
							indeks.setListaSpolek(listaWybranychElementow);							
							Gielda g = Swiat.listaWsyztskichGield.get(0);//bierze istniej¹c¹ gie³dê i dodaje nowy indeks do jej listy indeksów		
							g.getListaIndeksow().add(indeks);
							textField_1.setText("");
						}else textField_1.setText("B³¹d, wype³nij wszytskie pola!");	
					}else textField_1.setText("B³¹d, brak gie³dy");					
									
					//TEST
//					Swiat.listaWsyztskichGield.get(0).getListaIndeksow();
//					for (Indeks ind : Swiat.listaWsyztskichGield.get(0).getListaIndeksow()) {
//						System.out.println(ind.getNazwa());
//					}			
					
				}
				else if (wyborObiektu==4) {//Spó³ka
					
					if(!Swiat.listaWsyztskichGield.isEmpty()) {//je¿eli istnieje gie³da			
							Spolka s = new Spolka();
							s.setNazwa(textField_1.getText());
							s.setLiczbaAkcji(Integer.parseInt(textField_2.getText()));
							s.setZysk(Double.parseDouble(textField_3.getText()));
							s.setPrzychód(Double.parseDouble(textField_4.getText()));
							s.setKapitalWlasny(Double.parseDouble(textField_5.getText()));
							s.setKapitalZalkadowy(Double.parseDouble(textField_6.getText()));
							s.setWolumen(Integer.parseInt(textField_7.getText()));
							s.setObroty(Double.parseDouble(textField_8.getText()));
							Akcja a = new Akcja();
							a.setNazwa(textField_1.getText());
							a.setDataPierwszejWyceny(textFieldDataWyceny.getText());
							a.setKursOtwarcia(Double.parseDouble(textFieldKursOtwarcia.getText()));
							a.setAktualnyKurs(Double.parseDouble(textFieldAktualnyKurs.getText()));
							a.setMinKurs(Double.parseDouble(textFieldMinKurs.getText()));
							a.setMaxKurs(Double.parseDouble(textFieldMaxKurs.getText()));
							s.setAkcja(a);//dodaje akcje do Spó³ki
							Gielda.listaWsyztskichSpolek.add(s);		
							Swiat.przyrostAktywowNaRynkach+=s.getLiczbaAkcji();
							
							//Uruchomienie w¹tku spó³ki
							s.start();
							
							textField_1.setText("");
							textField_2.setText("");
							textField_3.setText("");
							textField_4.setText("");
							textField_5.setText("");
							textField_6.setText("");
							textField_7.setText("");
							textField_8.setText("");
							textFieldDataWyceny.setText("");
							textFieldKursOtwarcia.setText("");
							textFieldAktualnyKurs.setText("");
							textFieldMinKurs.setText("");
							textFieldMaxKurs.setText("");
					}else textField_1.setText("B³¹d, brak gie³dy");
				}
				else if (wyborObiektu==5) {//Waluta
					if(!Swiat.listaWsyztskichRynkowWalut.isEmpty()) {//je¿eli istnieje rynek walut
						Waluta w = new Waluta();
						w.setNazwa(textField_1.getText());
						w.setCenaKupna(Double.parseDouble(textField_2.getText()));
						w.setCenaSprzedazy(Double.parseDouble(textField_3.getText()));
						for (int i = 0; i < tablicaKrajowDlaWaluty.length; i++) {
							String s = tablicaKrajowDlaWaluty[i];
							w.getListaKrajow().add(s);
						}				
						RynekWalut.listaWszytskichWalut.add(w);
						Swiat.przyrostAktywowNaRynkach+=40000;
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textAreaKraje.setText("");
						
//						TEST
//						for (String string : w.getListaKrajow()) {
//							System.out.println(string);
//						}
						
					}else textField_1.setText("B³¹d, brak rynku walut");
					
				}
				else if (wyborObiektu==6) {//Surowiec
					if(!Swiat.listaWsyztskichRynkowSurowcow.isEmpty()) {//je¿eli istnieje rynek surowców
						Surowiec s = new Surowiec();
						s.setNazwa(textField_1.getText());
						s.setJednostkaHandlowa(textField_2.getText());
						s.setWaluta(wylosowanaWaluta);
						s.setAktualnaWartosc(Double.parseDouble(textField_4.getText()));
						s.setMinWartosc(Double.parseDouble(textField_5.getText()));
						s.setMaxWartosc(Double.parseDouble(textField_6.getText()));
						RynekSurowcow.listaWszystkichSurowcow.add(s);
						Swiat.przyrostAktywowNaRynkach+=40000;
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
						textField_5.setText("");
						textField_6.setText("");
					}else textField_1.setText("B³¹d, brak rynku surowców");
					
				}
				else if (wyborObiektu==7) {//Inwestor indywidualny
					InwestorIndywidualny inw = new InwestorIndywidualny();
					inw.setImie(textField_1.getText());
					inw.setNazwisko(textField_2.getText());
					inw.setPesel(textField_3.getText());
					inw.setBudzet(Double.parseDouble(textField_4.getText()));

					Swiat.listaWszytskichInwestorowIndywidualnych.add(inw);
					
					//start w¹tku
					Thread watekInwestora = new Thread(inw);
					watekInwestora.start();
					
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");					
				}
				else if (wyborObiektu==8) {//Fundusz inwestycyjny
					FunduszInwestycyjny finw = new FunduszInwestycyjny();
					finw.setNazwa(textField_1.getText());
					finw.setImie(textField_2.getText());
					finw.setNazwisko(textField_3.getText());
					
					Swiat.listaWszytskichFunduszyInwestycyjnych.add(finw);
					
					//start w¹tku
					Thread watekFunduszu = new Thread(finw);
					watekFunduszu.start();
					
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");				
				}
				btnDodajObiekt.setEnabled(false);
				
			}
		});
	
	}
}
