package es.urjccode.mastercloudapps.adcs.dobles.injectMocks;

public class DOC {

	private int attribute;

	public void exerciseDOC(boolean value) {
        System.out.println("DOC");
	}

	public int get(){
		return 666;
	}

	public void set(int value) {
		this.attribute = value;
	}
    
}