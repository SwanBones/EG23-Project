package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Zones {
	private static ArrayList<Student> reserve = new ArrayList();
    private static ArrayList<Student> bde = new ArrayList();
    private static ArrayList<Student> halleIndus = new ArrayList();
    private static ArrayList<Student> halleSport = new ArrayList();
    private static ArrayList<Student> bu = new ArrayList();
    private static ArrayList<Student> quartierAdmin = new ArrayList();
    
    private static Map<ArrayList<Student>,String> zoneArrayList = new HashMap<>();;
    
    public static void clearLists() {
    	reserve.clear();
    	bde.clear();
    	halleIndus.clear();
    	halleSport.clear();
    	bu.clear();
    	quartierAdmin.clear();
    }
    
    public static void fillZoneArrayList() {
    	zoneArrayList.put(bde,"bde");
    	zoneArrayList.put(quartierAdmin,"quartierAdmin");
    	zoneArrayList.put(halleSport,"halleSport");
    	zoneArrayList.put(halleIndus,"halleIndus");
    	zoneArrayList.put(bu,"bu");
    	
    }
    
    
    public static void fillReserve() {
    	for (int i = 1; i<=20;i++) {
    		Student newStudent = new Student("Soldat "+i);
    		reserve.add(newStudent);
    		newStudent.setZone(reserve);;
    		//System.out.println(newStudent.getName());
    	}
    }
    
    public static void reset() {
		Zones.fillReserve();
    	Zones.fillZoneArrayList();
	}
    
    public static Map<ArrayList<Student>,String> getZoneArrayList() {
		return zoneArrayList;
	}

	public static void setZoneArrayList(Map<ArrayList<Student>,String> zoneArrayList) {
		Zones.zoneArrayList = zoneArrayList;
	}

	public static void moveStudent(Student student, ArrayList<Student> from, ArrayList<Student> to) {
    	to.add(student);
    	from.remove(student);
    	student.setZone(to);
    }
    
    public ArrayList<Student> getReserve() {
		return reserve;
	}
	public void setReserve(ArrayList<Student> reserve) {
		this.reserve = reserve;
	}
	public ArrayList<Student> getBde() {
		return bde;
	}
	public void setBde(ArrayList<Student> bde) {
		this.bde = bde;
	}
	public ArrayList<Student> getHalleIndus() {
		return halleIndus;
	}
	public void setHalleIndus(ArrayList<Student> halleIndus) {
		this.halleIndus = halleIndus;
	}
	public ArrayList<Student> getHalleSport() {
		return halleSport;
	}
	public void setHalleSport(ArrayList<Student> halleSport) {
		this.halleSport = halleSport;
	}
	public ArrayList<Student> getBu() {
		return bu;
	}
	public void setBu(ArrayList<Student> bu) {
		this.bu = bu;
	}
	public ArrayList<Student> getQuartierAdmin() {
		return quartierAdmin;
	}
	public void setQuartierAdmin(ArrayList<Student> quartierAdmin) {
		this.quartierAdmin = quartierAdmin;
	}
	
	public Zones() {
		// TODO Auto-generated constructor stub
	}

	

}
