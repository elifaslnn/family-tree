import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class kisi {
	
	public String id;
	public String ad;
	public String soyad;
	public String dogumTarihi;
	public Date dogumTarihi_date;
	public String es;
	public String anneAdi;
	public String babaAdi;
	public String kanGrubu;
	public String meslek;
	public String medeniHal;
	public String kizlikSoyadi;
    public String cinsiyet;	
	public kisi anne;
	public kisi baba;
	public kisi k_es;
	public kisi kardes;
	public kisi kuzen;
	//////////////////////////////////////
	/////////////////////////////////////
	public List<kisi> kardesler =new ArrayList<kisi>();
    public  List<kisi> children=new ArrayList<kisi>(); 
	public List<kisi> kuzenler =new ArrayList<kisi>();
	public List<kisi> derece=new ArrayList<kisi>();

	public kisi root;

	kisi(){

	}

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public String getDogumTarihi() {
		return dogumTarihi;
	}
	public void setDogumTarihi(String dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}
	public String getAnneAdi() {
		return anneAdi;
	}
	public void setAnneAdi(String anneAdi) {
		this.anneAdi = anneAdi;
	}
	public String getBabaAdi() {
		return babaAdi;
	}
	public void setBabaAdi(String babaAdi) {
		this.babaAdi = babaAdi;
	}
	public String getKanGrubu() {
		return kanGrubu;
	}
	public void setKanGrubu(String kanGrubu) {
		this.kanGrubu = kanGrubu;
	}
	public String getMeslek() {
		return meslek;
	}
	public void setMeslek(String meslek) {
		this.meslek = meslek;
	}
	public String getKizlikSoyadi() {
		return kizlikSoyadi;
	}
	public void setKizlikSoyadi(String kizlikSoyadi) {
		this.kizlikSoyadi = kizlikSoyadi;
	}
	public String getEs() {
		return es;
	}
	public void setEs(String es) {
		this.es = es;
	}
	public String getMedeniHal() {
		return medeniHal;
	}
	public void setMedeniHal(String medeniHal) {
		this.medeniHal = medeniHal;
	}
 	public List<kisi> getChildren() {
		return children;
	}
	public void setChildren(List<kisi> children) {
		this.children = children;
	}
	public kisi getRoot() {
		return root ;
	}
	public void setRoot(kisi root) {
		this.root = root;
	}
    public String getCinsiyet() {
		return cinsiyet;
	}
	public void setCinsiyet(String cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	/////////////////////////////////////////////////////////
	///////ADD CHILD////////////////////////
	public void addChild(kisi root,kisi child_node)
	{
        root.children.add(child_node);
	}

	public void addKardes(kisi root,kisi kardes_node)
	{
        root.kardesler.add(kardes_node);
	}

	public void addKuzen(kisi root,kisi kuzen_node)
	{
        root.kuzenler.add(kuzen_node);
	}

    public void addDerece(kisi root,kisi derece_node)
	{
        root.derece.add(derece_node);
	}


}