import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Array;
import java.nio.channels.ScatteringByteChannel;
import java.rmi.server.RemoteServer;
import java.security.AlgorithmConstraints;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;
/*import javax.lang.model.util.SimpleTypeVisitor7;
import javax.print.attribute.standard.PrinterName;
import javax.security.auth.DestroyFailedException;
import javax.sql.rowset.spi.SyncResolver;*/
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.plaf.synth.SynthPasswordFieldUI;
/*import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Graphics;
import java.awt.Graphics2D;*/
import javax.swing.text.AsyncBoxView.ChildState;

public class App {

	public static void main(String[] args) throws Exception {

		ArrayList <String> path=new ArrayList<>();

        path.add(".\\Prolab 3.csv");
		path.add(".\\Prolab 3 2.csv");
		path.add(".\\Prolab 3 3.csv");
		path.add(".\\Prolab 3 4.csv");

		/////////////////////TREE/////////////////////////////////
		String line="";
		
		List<kisi> allPeople=new ArrayList<>();

 		int row=0;

		 try{
			BufferedReader br=new BufferedReader(new FileReader(path.get(0)));
			while((line=br.readLine())!=null)
			{
				//System.out.println(line);
				String[] values=line.split(";");
				if(values.length!=0)
				{
					kisi kisi=new kisi();
					kisi.id=values[0];
					kisi.ad=values[1];
				   kisi.soyad=values[2];
					kisi.dogumTarihi=values[3];
					kisi.es=values[4];
					//kisi.esId=values[5];
				kisi.anneAdi=values[5];
				kisi.babaAdi=values[6];
					kisi.kanGrubu=values[7];
					kisi.meslek=values[8];
					kisi.medeniHal=values[9];
					kisi.kizlikSoyadi=values[10];
					kisi.cinsiyet=values[11];
					allPeople.add(kisi);
				}
			}
			
		}
	   
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}

		int i,j,k;
		ArrayList<Integer> family=new ArrayList<>();
		ArrayList<kisi> allPeople_temp=new ArrayList<>();

		

		for(i=0;i<allPeople.size();i++){
			for(j=i+1;j<allPeople.size();j++){		
				if ( allPeople.get(i).id.equals(allPeople.get(j).id)){
					allPeople.remove(allPeople.get(j));
				}
			}
		}
	
		

		for(i=0;i<allPeople.size();i++){
			System.out.println(allPeople.get(i).id+"="+ allPeople.get(i).ad);
		}


		for(i=0;i<allPeople.size();i++)
		{
			for(j=0;j<allPeople.size();j++)
			{
				boolean check=allPeople.get(i).es.equals("");
				if(allPeople.get(i).ad.equals(allPeople.get(j).anneAdi) && (allPeople.get(i).es.contains(allPeople.get(j).babaAdi) ) && (check==false)){
					if(allPeople.get(j).cinsiyet.equals("Kadın") && allPeople.get(j).kizlikSoyadi.equals(allPeople.get(i).soyad)){

					allPeople.get(i).addChild(allPeople.get(i), allPeople.get(j));
					allPeople.get(j).anne=allPeople.get(i);}
					else{
					allPeople.get(i).addChild(allPeople.get(i), allPeople.get(j));
					allPeople.get(j).anne=allPeople.get(i);

					}
				}else if(allPeople.get(i).ad.equals(allPeople.get(j).babaAdi) && (allPeople.get(i).es.contains(allPeople.get(j).anneAdi) ) &&(check==false)){
					if(allPeople.get(j).cinsiyet.equals("Kadın") && allPeople.get(j).kizlikSoyadi.equals(allPeople.get(i).soyad)){
					allPeople.get(i).addChild(allPeople.get(i), allPeople.get(j));
					allPeople.get(j).baba=allPeople.get(i);
					}else{
						allPeople.get(i).addChild(allPeople.get(i), allPeople.get(j));
					    allPeople.get(j).baba=allPeople.get(i);
					}
				}
			}
		}
	

		for(i=0;i<allPeople.size();i++){
			System.out.print(allPeople.get(i).ad+"--->");
			for(j=0;j<allPeople.get(i).children.size();j++){
				System.out.print(allPeople.get(i).children.get(j).ad+" ");
			}
			System.out.println();
		}

		ArrayList<kisi> root=new ArrayList<>();
		for(i=0;i<allPeople.size();i++){
			if(allPeople.get(i).anne==null && allPeople.get(i).baba==null){
				root.add(allPeople.get(i));
			}
		}
		for(i=0;i<root.size();i++){
			System.out.println("root="+root.get(i).ad);
		}


		////////////////////KİsİNİN EsİNİ BULMA FONK/////////////////////////////

		for(i=0;i<allPeople.size();i++){
			for(j=i+1;j<allPeople.size();j++){
				boolean check= allPeople.get(i).equals(allPeople.get(j));
				if(check==false && allPeople.get(i).children.equals(allPeople.get(j).children) && allPeople.get(i).children.size()!=0){
					allPeople.get(i).k_es=allPeople.get(j);
					allPeople.get(j).k_es=allPeople.get(i);
				}
			}
		}


		ArrayList<kisi> nullEs=new ArrayList<>();

		for(i=0;i<allPeople.size();i++){
			if(allPeople.get(i).k_es==null){
				kisi esi=new kisi();
				esi.ad=allPeople.get(i).es;
				esi.k_es=allPeople.get(i);
				esi.soyad=allPeople.get(i).soyad;
				esi.children=allPeople.get(i).children;
				allPeople.get(i).k_es=esi;
				nullEs.add(esi);
				//allPeople.add(esi);
				if(allPeople.get(i).cinsiyet.equals("Erkek")){
						esi.cinsiyet="Kadın";
				}else{
					esi.cinsiyet="Erkek";
				}
			}
			if(allPeople.get(i).baba==null){
				kisi baba=new kisi();
				baba.ad=allPeople.get(i).babaAdi;
				allPeople.get(i).baba=baba;
			}
			if(allPeople.get(i).anne==null){
				kisi anne=new kisi();
				anne.ad=allPeople.get(i).anneAdi;
				allPeople.get(i).anne=anne;
			}
		}
		

		System.out.println("******");

		for(i=0;i<nullEs.size();i++){
			for(j=0;j<nullEs.get(i).children.size();j++){
				if(nullEs.get(i).cinsiyet.equals("Kadın")){
				    nullEs.get(i).children.get(j).anne=nullEs.get(i);
				}
				else{
                    nullEs.get(i).children.get(j).baba=nullEs.get(i);
				}
			}
		}



		



		for(i=0;i<allPeople.size();i++){
			allPeople_temp.add(allPeople.get(i));
		}

        ////////////////////////NESİL ATAMASI/////////////////////////////////////
		ArrayList<ArrayList <kisi>> nesil_first=new ArrayList<ArrayList <kisi>>(6);

		for(i=0; i < 6; i++) {
			nesil_first.add(new ArrayList());
		}


		for(i=1;i<root.size()-1;i++){
			nesil_first.get(0).add(root.get(i));
		}
		

		///////////////////////////////////////////////////////////
		for(k=0;k<nesil_first.size();k++){
			for(i=0;i<nesil_first.get(k).size();i++){
				for(j=0;j<nesil_first.get(k).get(i).children.size();j++){
					nesil_first.get(k+1).add(nesil_first.get(k).get(i).children.get(j));
					boolean check=(nesil_first.get(k).get(i).children.get(j).k_es.ad.equals(""));
					if(check==false)
						nesil_first.get(k+1).add(nesil_first.get(k).get(i).children.get(j).k_es);
				allPeople.remove(nesil_first.get(k).get(i));
				allPeople.remove(nesil_first.get(k).get(i).k_es);
			}
		}
	}
		//////////////////////////////////////////////////////////////////////////////////
		for(k=0;k<nesil_first.size();k++){
		    for(i=0;i<nesil_first.size();i++){
			    for(j=0;j<nesil_first.get(i).size();j++){
				    for(k=j+1;k<nesil_first.get(i).size();k++){
					    if(nesil_first.get(i).get(j).equals(nesil_first.get(i).get(k))){
						    nesil_first.get(i).remove(nesil_first.get(i).get(j));
					    }
				    }
				}	
			}
		}
	
	System.out.println("////////////////////////////");
	System.out.println("////////////////////////////");
	System.out.println("////////////////////////////");
    System.out.println(nesil_first.size());
		for(k=4;k>=1;k--){

	   for(int m=0;m<k;m++){
		for(i=0;i<nesil_first.get(k).size();i++){
			for(j=0;j<nesil_first.get(m).size();j++){
				if(nesil_first.get(k).get(i).equals(nesil_first.get(m).get(j))){
					nesil_first.get(m).remove(nesil_first.get(k).get(i));
					nesil_first.get(m).remove(nesil_first.get(k).get(i).k_es);
				}
			}
			}
		 }
		 System.out.println("tur");
	    }

		for(i=0;i<nesil_first.size();i++){
			for(j=0;j<nesil_first.get(i).size();j++){
				for(k=j+1;k<nesil_first.get(i).size();k++){
					if(nesil_first.get(i).get(j).equals(nesil_first.get(i).get(k))){
						nesil_first.get(i).remove(nesil_first.get(i).get(j));
					}
				}
			}

		}
		for(i=0;i<nesil_first.size();i++){
			for(j=0;j<nesil_first.get(i).size();j++){
				for(k=j+1;k<nesil_first.get(i).size();k++){
					if(nesil_first.get(i).get(j).equals(nesil_first.get(i).get(k))){
						nesil_first.get(i).remove(nesil_first.get(i).get(j));
					}
				}
			}

		}
		
		//////////////////////////////////JFRAME/////////////////////////////////////////////////
		JFrame jf=new JFrame();
		jf.setSize(1500,1000);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setLayout(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Color cl=new Color(253,204,204);

		int y=100;
		int width=80;


		ArrayList<JLabel> childLabel_temp= new ArrayList<>();
        
		for(i=0;i<nesil_first.size();i++){
			ArrayList<JLabel> childLabel= new ArrayList<>();

		    int aralik=((1500-(nesil_first.get(i).size()*width)))/(nesil_first.get(i).size()+1);
			int baslangic=0;
			for(j=0;j<nesil_first.get(i).size();j++){
				//int a=0,b=0,c=0,d;
				Boolean checkMan=nesil_first.get(i).get(j).cinsiyet.equals("Erkek");
				Boolean checkWoman=(nesil_first.get(i).get(j).cinsiyet.equals("Kadın") || nesil_first.get(i).get(j).cinsiyet.equals("KadÄ±n"));
				System.out.println(nesil_first.get(i).get(j).ad+":"+nesil_first.get(i).get(j).cinsiyet);
				JLabel child=new JLabel();
				child.setText(nesil_first.get(i).get(j).ad+" "+nesil_first.get(i).get(j).soyad);
				child.setOpaque(true);
				if(checkWoman==true){
			        child.setBackground(Color.pink);
				}
				else if(checkMan==true){
					child.setBackground(Color.lightGray);
				}
                child.setFont(new Font("Times New Roman",Font.BOLD,10));
				childLabel.add(child);
				childLabel_temp.add(child);

			    jf.add(child);
			}
			for(j=0;j<nesil_first.get(i).size();j++){
				childLabel.get(j).setBounds(baslangic+aralik, y, width, 50);
				baslangic=baslangic+width+aralik;
	     }
	         y=y+150;
		}

		jf.setVisible(true);


		/////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////İSTER-1/////////////////////////////////////////////////////////
		System.out.println("1)Çocugu olmayan dugumlerin listesinin yas siralamasina gore kaydedilmesi istenmektedir.");
		System.out.println("2)Uvey kardesler bulunarak harf siralamasina gore kaydedilecektir.");
		System.out.println("3)Kan grubu A olanlarin listesi kaydedilerek gosterilecektir.");
		System.out.println("4)Soyunda ayni meslegi yapan cocuklar veya torunlar gosterilecektir. ");
		System.out.println("5)Soy agacinda ayni isme sahip kisilerin ismi ve yaslari gosterilecektir.");
		System.out.println("6)Kullanicidan alinacak 2 tane isim girdisinden sonra buyuk olan kisinin kucuk olan kisiye yakinligi gosterilecektir. ");
		System.out.println("7)Kullanicidan alinan kisi bilgisi ile o kisiye ait soy agacinin gosterilmesi istenmektedir.");
		System.out.println("8)Soy agacinin kaç nesilden olustugu bulunacaktir. ");
		System.out.println("9)Kullanicidan alinan isim girdisinden sonra o isimden sonra kaç nesil geldigi bulunacaktır.");
		System.out.println("10)Yukaridaki tum isterler için en iyi ve en kotu senaryo karmasiklik hesabi yapilacaktır.");
		System.out.println("Yapmak istediginiz islemi giriniz");
		Scanner key=new Scanner(System.in);
		int secim=key.nextInt();

		switch(secim){
			case 1://Çocugu olmayan dugumlerin listesinin yas sıralamasına gore kaydedilmesi istenmektedir.

			ArrayList <kisi> childless=new ArrayList<>();
			ArrayList <Date> childless_Age=new ArrayList<>();
	
			for(i=0;i<allPeople.size();i++){
				boolean check=allPeople.get(i).ad.equals("Ä°sim");
				boolean check2=allPeople.get(i).ad.equals("İsim");
				if(allPeople.get(i).children.size()==0 && (check==false && check2==false)){
					System.out.println(allPeople.get(i).ad);
					childless.add(allPeople.get(i));
				}
			}
			for(i=0;i<childless.size();i++){
				String ageStr=childless.get(i).dogumTarihi;
				Date date=new SimpleDateFormat("dd/MM/yyy").parse(ageStr);
				childless_Age.add(date);
				childless.get(i).dogumTarihi_date=date;
			}
			ArrayList<Date> yasSiralama=new ArrayList<>();
	
			Date max=childless.get(0).dogumTarihi_date;
			
			for(i=0;i<childless_Age.size();i++){
	
				for(j=i+1;j<childless_Age.size();j++){
					if(childless_Age.get(i).compareTo(childless_Age.get(j))>0){
					   // Date temp;
						Collections.swap(childless_Age,i, j);
						Collections.swap(childless,i, j);
					}
				
				}
			}
			for(i=0;i<childless_Age.size();i++){
				System.out.println(childless.get(i).ad+"|"+childless.get(i).dogumTarihi_date);
			}

			break;
			case 2:
			////////////////////////////////////////////////////////////////////////////////
		    ///////////////////////////İSTER-2//////////////////////////////////////////////
		    //uvey kardesler bulunarak harf sıralamasına gore kaydedilecektir.

			for(i=0;i<allPeople_temp.size();i++){
				ArrayList <kisi> kardesler=new ArrayList<>();
				for(j=0;j<allPeople_temp.get(i).children.size();j++){
                     kardesler.add(allPeople_temp.get(i).children.get(j));
				}

				for(j=0;j<kardesler.size();j++){
					for(k=j+1;k<kardesler.size();k++){
						Boolean checkAnne= kardesler.get(j).anne.equals(kardesler.get(k).anne);
						Boolean checkBaba= kardesler.get(j).baba.equals(kardesler.get(k).baba);

						if( checkAnne==false || checkBaba==false){
							System.out.println(kardesler.get(j).ad+" üvey");
						}
					}
				}
			}

		   break;
		   case 3:
		   ////////////////////////////////////////////////////////////////////////////////
		///////////////////////////İSTER-3//////////////////////////////////////////////
		//Kan grubu A olanların listesi kaydedilerek gosterilecektir.
		ArrayList <kisi> A_kanGrubu=new ArrayList<>();
		for(i=0;i<allPeople.size();i++){
			System.out.println(allPeople.get(i).ad);
		}


		for(i=0;i<allPeople_temp.size();i++){
			if(allPeople_temp.get(i).id.equals("id")){
				allPeople_temp.remove(allPeople_temp.get(i));
			}
		}
		for(i=0;i<allPeople_temp.size();i++){
			//System.out.println(allPeople_temp.get(i).kanGrubu);
			if(allPeople_temp.get(i).kanGrubu.equals("A(-)") || allPeople_temp.get(i).kanGrubu.equals("A(+)")){
				A_kanGrubu.add(allPeople_temp.get(i));
				System.out.println(allPeople_temp.get(i).ad+"-"+allPeople_temp.get(i).kanGrubu);
			}
		} 
		break;
		case 4:
		////////////////////////////////////////////////////////////////////////////////
		///////////////////////////İSTER-4//////////////////////////////////////////////
		//Soyunda aynı meslegi yapan çocuklar veya torunlar gosterilecektir. 

		for(i=0;i<allPeople_temp.size();i++){
			if(allPeople_temp.get(i).baba!=null){
			if(allPeople_temp.get(i).meslek.equals(allPeople_temp.get(i).baba.meslek)&&allPeople_temp.get(i).meslek.equals(allPeople_temp.get(i).baba.baba.meslek)){
				System.out.println(allPeople_temp.get(i).ad+" babasının ve dedesinin meslegini davam ettiriyor->"+allPeople_temp.get(i).meslek);
				System.out.println("Babasi:"+allPeople_temp.get(i).baba.ad);
				System.out.println("Dedesi:"+allPeople_temp.get(i).baba.baba.ad);
			}else if(allPeople_temp.get(i).meslek.equals(allPeople_temp.get(i).baba.meslek)){
				System.out.println(allPeople_temp.get(i).ad+" babasının meslegini davam ettiriyor->"+allPeople_temp.get(i).meslek);
				System.out.println("Babasi:"+allPeople_temp.get(i).baba.ad);
			}
		}
		}
		break;

		case 5:
				////////////////////////////////////////////////////////////////////////////////
		///////////////////////////İSTER-5//////////////////////////////////////////////
		//Soy agacında aynı isme sahip kisilerin ismi ve yasları gosterilecektir
		ArrayList <kisi> soyAgaci_kisiler = new ArrayList<>();

		for(i=0;i<nesil_first.size();i++){
			for(j=0;j<nesil_first.get(i).size();j++){
				soyAgaci_kisiler.add(nesil_first.get(i).get(j));
			}
		}
		for(i=0;i<soyAgaci_kisiler.size();i++){
			System.out.println(soyAgaci_kisiler.get(i).ad);
		}
		for(i=0;i<soyAgaci_kisiler.size();i++){
			for(j=i+1;j<soyAgaci_kisiler.size();j++){
				if(soyAgaci_kisiler.get(j).ad.contains(soyAgaci_kisiler.get(i).ad)){
					System.out.println("bulunan kisiler ayni isme sahiptir");
					System.out.println(soyAgaci_kisiler.get(i).ad+"->"+soyAgaci_kisiler.get(i).dogumTarihi);
					System.out.println(soyAgaci_kisiler.get(j).ad+"->"+soyAgaci_kisiler.get(j).dogumTarihi);

				}
			}
		}
		break;

		case 6:
		////////////////////////////////////////////////////////////////////////////////
		///////////////////////////İSTER-6//////////////////////////////////////////////
		//Kullanıcıdan alınacak 2 tane isim girdisinden sonra buyuk olan kisinin kuçuk olan kisiye yakınlıgı gosterilecektir.
		//Scanner key=new Scanner(System.in);
		System.out.println("Yakinligini bulmak istediginiz kisileri isim soyisim olark giriniz");
		Scanner key3=new Scanner(System.in);
		String isim_1=key3.nextLine();
		System.out.println("alinan kisi: "+isim_1);
		System.out.println("Yakinligini bulmak istediginiz kisileri isim soyisim olark giriniz");

		Scanner key2=new Scanner(System.in);

		String isim_2=key2.nextLine();
		System.out.println("alinan isim "+isim_2);

		kisi aranan_1=new kisi();
		kisi aranan_2=new kisi();

		for(i=0;i<allPeople_temp.size();i++){
			String temp=allPeople_temp.get(i).ad+" "+allPeople_temp.get(i).soyad;
			if(temp.equals(isim_1)){
				System.out.println("bulunan kisi: "+temp);
				aranan_1=allPeople_temp.get(i);
			}

		}
		for(i=0;i<allPeople_temp.size();i++){
			String temp=allPeople_temp.get(i).ad+" "+allPeople_temp.get(i).soyad;
			if(temp.equals(isim_2)){
				System.out.println("bulunan kisi: "+temp);
				aranan_2=allPeople_temp.get(i);
			}
		}
		System.out.println(aranan_1.dogumTarihi);
		System.out.println(aranan_2.dogumTarihi);

		    String age_1Str=aranan_1.dogumTarihi;
			Date date_1=new SimpleDateFormat("dd/MM/yyyy").parse(age_1Str);
			aranan_1.dogumTarihi_date=date_1;
			String age_2Str=aranan_2.dogumTarihi;
			Date date_2=new SimpleDateFormat("dd/MM/yyyy").parse(age_2Str);
			aranan_2.dogumTarihi_date=date_2;


		String family_path="";
		if(aranan_1.dogumTarihi_date.compareTo(aranan_2.dogumTarihi_date)<0){
			System.out.println(aranan_1.ad+" "+aranan_2.ad+"'dan daha buyuktur");
			System.out.println(aranan_1.dogumTarihi+" |"+aranan_2.dogumTarihi);

			int check=0;
			family_path=aranan_2.ad;

			if(aranan_1.cinsiyet.equals("Kadın") || aranan_1.cinsiyet.equals("KadÄ±n"))
			{
			while(check!=1){
			    if(aranan_2.anne.equals(aranan_1)){
				family_path=family_path+"'ın annesi->"+aranan_1.ad;
				check=1;
				break;
				}
				if(aranan_2.cinsiyet.equals("Kadın") ||aranan_2.cinsiyet.equals("KadÄ±n")){
				  	aranan_2=aranan_2.anne;
					family_path=family_path+"'ın annesi->"+aranan_2.ad;
					//System.out.println(family_path);
				}
				else if(aranan_2.cinsiyet.equals("Erkek")){
					aranan_2=aranan_2.baba;
					family_path=family_path+"'ın babasi ->"+aranan_2.ad;
					//System.out.println(family_path);

				}
			}
		}
		else if(aranan_1.cinsiyet.equals("Erkek")){
			while(check!=1 && aranan_2.baba!=null){
			    if(aranan_2.baba.equals(aranan_1)){
				family_path=family_path+"'ın annesi->"+aranan_1.ad;
				check=1;
				break;
				}
				else if(aranan_2.cinsiyet.equals("Kadın") ||aranan_2.cinsiyet.equals("KadÄ±n")){
				  	aranan_2=aranan_2.anne;
					family_path=family_path+"'ın annesi->"+aranan_2.ad;
					//System.out.println(family_path);
				}else if(aranan_2.cinsiyet.equals("Erkek")){
					aranan_2=aranan_2.baba;
					family_path=family_path+"'ın babasi ->"+aranan_2.ad;
					//System.out.println(family_path);

				}
			}
		}
     }else{
			    System.out.println(aranan_1.ad+" "+aranan_2.ad+"'dan daha kucuktur");
			    System.out.println(aranan_1.dogumTarihi+" |"+aranan_2.dogumTarihi);

			int check=0;
			family_path=aranan_2.ad;

			if(aranan_2.cinsiyet.equals("Kadın") || aranan_2.cinsiyet.equals("KadÄ±n"))
			{
			while(check!=1){
			    if(aranan_1.anne.equals(aranan_1)){
				family_path=family_path+"'ın annesi->"+aranan_2.ad;
				check=1;
				}
				else if(aranan_1.cinsiyet.equals("Kadın") ||aranan_1.cinsiyet.equals("KadÄ±n")){
				  	aranan_1=aranan_1.anne;
					family_path=family_path+"'ın annesi->"+aranan_1.ad;
					//System.out.println(family_path);
				}else if(aranan_1.cinsiyet.equals("Erkek")){
					aranan_1=aranan_2.baba;
					family_path=family_path+"'ın babasi ->"+aranan_1.ad;
					//System.out.println(family_path);

				}
			}
		}
		else if(aranan_2.cinsiyet.equals("Erkek")){
			while(check!=1 && aranan_1.baba!=null){
			    if(aranan_1.baba.equals(aranan_2)){
				family_path=family_path+"'ın annesi->"+aranan_2.ad;
				check=1;
				}
				else if(aranan_1.cinsiyet.equals("Kadın") ||aranan_1.cinsiyet.equals("KadÄ±n")){
				  	aranan_1=aranan_1.anne;
					family_path=family_path+"'ın annesi->"+aranan_1.ad;
					//System.out.println(family_path);
				}else if(aranan_1.cinsiyet.equals("Erkek")){
					aranan_1=aranan_1.baba;
					family_path=family_path+"'ın babasi ->"+aranan_1.ad;
					//System.out.println(family_path);

				}
			}
		}
		}
		System.out.println(family_path);
		
			break;
			 case 7:
			 ////////////////////////////////////////////////////////////////////////////////
		///////////////////////////İSTER-7//////////////////////////////////////////////
		//Kullanıcıdan alınan kisi bilgisi ile o kisiye ait soy agacının gosterilmesi istenmektedir.
		System.out.println("Yakinligini bulmak istediginiz kisileri isim soyisim olark giriniz");
		Scanner key4=new Scanner(System.in);
		String isim_3=key4.nextLine();

		kisi aranan_3=new kisi();
		ArrayList <kisi> arananlar=new ArrayList<>();

		for(i=0;i<allPeople_temp.size();i++){
			String temp=allPeople_temp.get(i).ad+" "+allPeople_temp.get(i).soyad;
			if(temp.equals(isim_3)){
				System.out.println("bulunan kisi: "+temp);
				aranan_3=allPeople_temp.get(i);
				arananlar.add(aranan_3);
				break;
			}
		}
		for(i=0;i<aranan_3.children.size();i++){
			arananlar.add(aranan_3.children.get(i));
			for(j=0;j<aranan_3.children.get(i).children.size();j++){
				arananlar.add(aranan_3.children.get(i).children.get(j));
				for(k=0;k<aranan_3.children.get(i).children.get(j).children.size();k++){
					arananlar.add(aranan_3.children.get(i).children.get(j).children.get(k));
					for(int m=0;m<aranan_3.children.get(i).children.get(j).children.get(k).children.size();m++){
						arananlar.add(aranan_3.children.get(i).children.get(j).children.get(k).children.get(m));
						for(int o=0;o<aranan_3.children.get(i).children.get(j).children.get(k).children.get(m).children.size();o++){
							arananlar.add(aranan_3.children.get(i).children.get(j).children.get(k).children.get(m).children.get(0));
						}
					}
				}
			}
		}
		
		for(i=0;i<arananlar.size();i++){
			System.out.println("arananlar:"+arananlar.get(i).ad);
		}

		int count=0;
		int break_loop=0;
		ArrayList <Integer> count_kisi=new ArrayList<>();


			for(k=0;k<arananlar.size();k++){
				count=0;
				for(i=0;i<nesil_first.size();i++){
					for(j=0;j<nesil_first.get(i).size();j++){
						if(arananlar.get(k).equals(nesil_first.get(i).get(j))){
							System.out.println("BULUNDU");
							count+=j;
							break_loop++;
							System.out.println("break:"+count);
							count_kisi.add(count);
						}	
					}
					count+=nesil_first.get(i).size();
				}
			}		
	   	 for(i=0;i< count_kisi.size();i++){
			childLabel_temp.get(count_kisi.get(i)).setBackground(Color.orange);
		}
		////////////////////////////////////////////////////////////////////////////
		//int check=0;
		for(i=1;i<arananlar.size();i++){
			String family_path_2="";
			int check=0;
			kisi aranan_5=new kisi();
			aranan_5=arananlar.get(i);
			System.out.println("aranan:"+aranan_5.ad+"|"+aranan_5.baba.ad);

			family_path_2=aranan_5.ad;

			if(aranan_3.cinsiyet.equals("Kadın") || aranan_3.cinsiyet.equals("KadÄ±n")){
				while(check!=1){
			    if(aranan_5.anne.equals(aranan_3)){
				family_path_2=family_path_2+"'ın annesi->"+aranan_3.ad;
				check=1;
				break;
				}
				for(j=0;j<arananlar.size();j++)
				{
					if(i!=j && aranan_5.anne!=null && aranan_5.anne.equals(arananlar.get(j)))
					{
							aranan_5=aranan_5.anne;
							family_path_2=family_path_2+"'ın annesi->"+aranan_5.ad;
						
					}
					else if(i!=j && aranan_5.baba!=null && aranan_5.baba.equals(arananlar.get(j)))
					{
						aranan_5=aranan_5.baba;
						family_path_2=family_path_2+"'ın babasi->"+aranan_5.ad;
					}
				}
			}
		    }
			  else if(aranan_3.cinsiyet.equals("Erkek")){
				while(check!=1){
			    if(aranan_5.baba.equals(aranan_3)){
				family_path_2=family_path_2+"'ın babasi->"+aranan_3.ad;
				check=1;
				break;
				}
				for(j=0;j<arananlar.size();j++)
				{
					if(i!=j && aranan_5.anne!=null && aranan_5.anne.equals(arananlar.get(j)))
					{
							aranan_5=aranan_5.anne;
							family_path_2=family_path_2+"'ın annesi->"+aranan_5.ad;
						
					}
					else if(i!=j && aranan_5.baba!=null && aranan_5.baba.equals(arananlar.get(j)))
					{
						aranan_5=aranan_5.baba;
						family_path_2=family_path_2+"'ın babasi->"+aranan_5.ad;
					}
				}

		    }
		}
			System.out.println(family_path_2);
		}
		break;

		case 8:
			    ////////////////////////////////////////////////////////////////////////////////
		///////////////////////////İSTER-8//////////////////////////////////////////////
		//Soy agacının kaç nesilden olustugu bulunacaktır. 
		
		int nesil_derinligi=0;
		for(i=0;i<nesil_first.size();i++){
			if(nesil_first.get(i).size()!=0)
              nesil_derinligi++;
		}
		System.out.println(nesil_derinligi);
		break;

		case 9:
				////////////////////////////////////////////////////////////////////////////////
		///////////////////////////İSTER-9//////////////////////////////////////////////
        //Kullanıcıdan alınan isim girdisinden sonra o isimden sonra kaç nesil geldigi bulunacaktır.
		System.out.println("Yakinligini bulmak istediginiz kisileri isim soyisim olarak giriniz");
		Scanner key5=new Scanner(System.in);
		String isim_4=key5.nextLine();
		kisi aranan_4=new kisi();

		ArrayList<kisi> arananlar_2=new ArrayList<>();
		ArrayList<Integer> arananlar2_count=new ArrayList<>();

		for(i=0;i<allPeople_temp.size();i++){
			String temp=allPeople_temp.get(i).ad+" "+allPeople_temp.get(i).soyad;
			if(temp.equals(isim_4)){
				System.out.println("bulunan kisi: "+temp);
				aranan_4=allPeople_temp.get(i);
				arananlar_2.add(aranan_4);
				break;
			}
		}

		for(i=0;i<aranan_4.children.size();i++){
			arananlar_2.add(aranan_4.children.get(i));
			for(j=0;j<aranan_4.children.get(i).children.size();j++){
				arananlar_2.add(aranan_4.children.get(i).children.get(j));
				for(k=0;k<aranan_4.children.get(i).children.get(j).children.size();k++){
					arananlar_2.add(aranan_4.children.get(i).children.get(j).children.get(k));
					for(int m=0;m<aranan_4.children.get(i).children.get(j).children.get(k).children.size();m++){
						arananlar_2.add(aranan_4.children.get(i).children.get(j).children.get(k).children.get(m));
						for(int o=0;o<aranan_4.children.get(i).children.get(j).children.get(k).children.get(m).children.size();o++){
							arananlar_2.add(aranan_4.children.get(i).children.get(j).children.get(k).children.get(m).children.get(0));
						}
					}
				}
			}
		}

		int index=arananlar_2.size()-1;

		int kisiSoyDerinligi=0;

		System.out.println("merhaba");
	for(k=0;k<arananlar_2.size();k++){
		for(i=0;i<nesil_first.size();i++){
			for(j=0;j<nesil_first.get(i).size();j++){
					if(arananlar_2.get(k).equals(nesil_first.get(i).get(j))){
						arananlar2_count.add(i);
					}

				}
		}
	}
        

		int rootIndex=arananlar2_count.get(0);
		int sonIndex=arananlar2_count.get(0);
		for(i=0;i<arananlar2_count.size();i++){
			for(j=i+1;j<arananlar2_count.size();j++){
				if(rootIndex>arananlar2_count.get(j)){
					rootIndex=arananlar2_count.get(j);
				}
				if(sonIndex<arananlar2_count.get(j)){
					sonIndex=arananlar2_count.get(j);
				}
			}
		}

		kisiSoyDerinligi=sonIndex-rootIndex;
		System.out.println(kisiSoyDerinligi);
		break;

		default:
		break;

		}

}
}

